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

@Repository("transactionDAO")
@Scope("request")
public class TransactionDAO {

	private final HibernateUtil hibernateUtility;
	private final StringBuilder hql;
	private final Lock lock;
	
	@Autowired
	public TransactionDAO(	@Value("#{hibernateUtil}") HibernateUtil hibernateUtility, 
							@Value("#{stringBuilder}") StringBuilder hql,
							@Value("#{lock}") Lock lock) {
		this.hibernateUtility = hibernateUtility;
		this.hql = hql;
		this.lock = lock;
	}

	/**
	 * @param hibernateUtility the hibernateUtility to set
	 * @throws RoutePlannerException 
	 */

	public final void createTransaction(_Transaction transaction) throws RoutePlannerException {
		lock.lock();
		Session session = openSession();
		Transaction hibernateTransaction = getTransaction(session);
		try	{
			session.save(transaction);
			hibernateTransaction.commit();
		} catch(HibernateException e) {
			hibernateTransaction.rollback();
			throw new RoutePlannerException(e, "Database connection error");
		} finally {
			lock.unlock();
			session.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public final List<_Transaction> getTransactionHistory(String userID) throws RoutePlannerException {
		Session session = openSession();
		hql.delete(0, hql.length());
		try {
			hql.append("FROM Transaction WHERE (user_id = '").append(userID).append("')");
			hql.append(" order by purchaseDate DESC");
			Query retrieveTransaction = session.createQuery(hql.toString());
			List<_Transaction> transactionList = (List<_Transaction>) retrieveTransaction.list();
			return transactionList;
		} catch (HibernateException e) {
			throw new RoutePlannerException("Invalid User parameter!");
		} finally {
			session.close();
		}
	}
	
	public final _Transaction retrieveTransaction(int transactionID) throws RoutePlannerException
	{
		Session session = openSession();
		hql.delete(0, hql.length());
		try {
			hql.append("FROM Transaction WHERE (transactionID= '").append(transactionID).append("')");
			Query retrieveTransaction= session.createQuery(hql.toString());
			_Transaction transaction= (_Transaction)retrieveTransaction.list().get(0);
			return transaction;
		} catch (HibernateException e) {
			throw new RoutePlannerException("Invalid Transaction parameter!");
		} finally {
			session.close();
		}
	}
	
	private final Session openSession() {
		Session session = hibernateUtility.getSessionFactory().openSession();
		return session;
	}
	
	/**
	 *  creates a Transaction object to connect to the database
	 */
	private final Transaction getTransaction(Session session) throws HibernateException {
		Transaction transaction = session.beginTransaction();
		transaction.begin();
		return transaction;
	}

	public final int retrieveHighestTransactionID() throws RoutePlannerException {
		Session session = openSession();
		hql.delete(0, hql.length());
		try {
			hql.append("SELECT MAX(transactionID) FROM Transaction");
			Query retrieveTransaction = session.createQuery(hql.toString());
			int highestID= (Integer) retrieveTransaction.list().get(0);
			return highestID;
		} catch (HibernateException e) {
			throw new RoutePlannerException("Invalid Transaction parameter!");
		} finally {
			session.close();
		}
	}
	
}
