package com.fdm.routeplanner.model.transactions;

import java.util.List;
import java.util.concurrent.locks.Lock;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.fdm.routeplanner.exception.RoutePlannerException;
import com.fdm.routeplanner.model.registration.HibernateUtil;

@Repository("ticketDAO")
@Scope("request")
public class TicketDAO {
	
	private final HibernateUtil hibernateUtility;
	private final StringBuilder hql;
	private final Lock lock;
	
	@Autowired
	public TicketDAO(@Value("#{hibernateUtil}") HibernateUtil hibernateUtility, 
					 @Value("#{stringBuilder}") StringBuilder hql,
					 @Value("#{lock}") Lock lock) {
		this.hibernateUtility = hibernateUtility;
		this.hql = hql;
		this.lock = lock;
	}
	
	@SuppressWarnings("unchecked")
	public final List<_Ticket> getTicketHistory(String transactionID) throws RoutePlannerException {
		Session session = openSession();
		hql.delete(0, hql.length());
		try {
			hql.append("from Ticket where (transactionID = '")
				.append(transactionID).append("')");
			hql.append("order by ticketID");
			Query query = session.createQuery(hql.toString());
	    	List<_Ticket> ticketList = (List<_Ticket>) query.list();
			return ticketList;
		} catch (HibernateException e) {
			throw new RoutePlannerException("Invalid Transaction parameter!");
		} finally {
			session.close();
		}
	}

	public final _Ticket retrieveTicket(int ticketID) throws RoutePlannerException{
		Session session = openSession();
        hql.delete(0, hql.length());
        try {
        	hql.append("from Ticket where (ticketID = '")
        				.append(ticketID).append("')");
        	Query query = session.createQuery(hql.toString());
            _Ticket ticket = (_Ticket) query.list().get(0);
            return ticket;
        } catch(HibernateException e) {
        	throw new RoutePlannerException("Invalid Ticket parameter!");
		} finally {
			session.close();
		}
	}

	public final void createTicket(_Ticket ticket) throws RoutePlannerException{
		lock.lock();
		Session session = openSession();
        Transaction transaction = createTransaction(session);
        try {	
        	session.save(ticket);
        	transaction.commit();
        } catch (HibernateException e) {
        	transaction.rollback();
        	throw new RoutePlannerException("Invalid ticket parameter!");
		} finally {
			lock.unlock();
			session.close();
		}
	}

	private final Session openSession() {
		Session session = hibernateUtility.getSessionFactory().openSession();
		return session;
	}
	
	private final Transaction createTransaction(Session session) throws HibernateException {
		Transaction transaction = session.beginTransaction();
		transaction.begin();
		return transaction;
	}
	
}

