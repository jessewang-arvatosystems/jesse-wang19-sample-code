package com.fdm.routeplanner.model.registration;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service("hibernateUtil")
public final class HibernateUtil {

	private final SessionFactory sessionFactory;

    @Autowired
	public HibernateUtil(@Value("#{sessionFactory}") SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
    public SessionFactory getSessionFactory() {
    	return sessionFactory;
    }

}