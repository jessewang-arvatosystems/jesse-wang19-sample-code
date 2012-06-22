package com.fdm.routeplanner.model.registration;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fdm.routeplanner.exception.RoutePlannerException;

@Service("registration")
public class Registration {
	
	private final HibernateUtil hibernateUtility;
	private final StringBuilder hql;
		
	@Autowired
	public Registration(@Value("#{stringBuilder}")StringBuilder hql,
						@Value("#{hibernateUtil}")HibernateUtil hibernateUtility) {
		this.hql = hql;
		this.hibernateUtility = hibernateUtility;
	}

	public final void registerUser(User user) throws RoutePlannerException{
		Session session = openSession();
        hql.delete(0, hql.length());
        
        Transaction transaction = createTransaction(session);
        hql.append("from User where (user_id = '").append(user.getUser_id()).append("')");
        try {
        	Query query = session.createQuery(hql.toString());
            @SuppressWarnings("rawtypes")
    		List results = query.list();
            if (results.isEmpty()) {
                session.save(user);
                transaction.commit();
                session.close();
    		}
            else {
            	session.close();
            	throw new RoutePlannerException("User already exists!");
    		}
        } catch (HibernateException e) {
        	transaction.rollback();
			session.close();
        	throw new RoutePlannerException("Invalid Registration parameter!");
		}
    }
	
	public final void loginUser(String userId, String pword) throws RoutePlannerException{
		Session session = openSession();
        hql.delete(0, hql.length());
        
        hql.append("from User where (user_id = '").append(userId).append("' and password = '").append(pword).append("')");
        
        try {
            Query query = session.createQuery(hql.toString());
            
            @SuppressWarnings("rawtypes")
            List results = query.list();
            session.close();
            if (results.isEmpty()) {
                throw new RoutePlannerException("Invalid username and/or password!");
    		}
        } catch (HibernateException e) {
			session.close();
        	throw new RoutePlannerException("Invalid Registeration parameter!");
		}        
    }
	
	private final Session openSession(){
		Session session = hibernateUtility.getSessionFactory().openSession();
		return session;
	}
	
	private final Transaction createTransaction(Session session) throws HibernateException {
		Transaction transaction = session.beginTransaction();
		transaction.begin();
		return transaction;
	}
}
