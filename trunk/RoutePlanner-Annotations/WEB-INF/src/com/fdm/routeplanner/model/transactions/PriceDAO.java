package com.fdm.routeplanner.model.transactions;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.fdm.routeplanner.exception.RoutePlannerException;
import com.fdm.routeplanner.model.registration.HibernateUtil;

@Repository("priceDAO")
@Scope("request")
public class PriceDAO {
	
	private final HibernateUtil hibernateUtility;
	private final StringBuilder stringBuilder;
	
	@Autowired
	public PriceDAO(@Value("#{hibernateUtil}") HibernateUtil hibernateUtility, 
					@Value("#{stringBuilder}") StringBuilder stringBuilder) {
		this.hibernateUtility = hibernateUtility;
		this.stringBuilder = stringBuilder;
	}

	public final BigDecimal retrieveAdultPrice(int zone) throws RoutePlannerException {
		return findPriceOfPerson("adult", zone);
	}

	public final BigDecimal retrieveJuniorPrice(int zone) throws RoutePlannerException {
		return findPriceOfPerson("junior", zone);
	}

	public final BigDecimal retrieveSeniorPrice(int zone) throws RoutePlannerException {
		return findPriceOfPerson("senior", zone);
	}
	
	@SuppressWarnings("unchecked")
	private final BigDecimal findPriceOfPerson(String person, int zone) throws RoutePlannerException {
		Session session;		
		List<BigDecimal> listOfPrices;
		String hSQLStatement;
		
		session = hibernateUtility.getSessionFactory().openSession();
		hSQLStatement = buildHSQLStatement(person, zone);
		listOfPrices = session.createQuery(hSQLStatement).list();
		session.close();
		return retrievePrice(listOfPrices);
	}
	
	private final String buildHSQLStatement(String person, int zone) {
		stringBuilder.delete(0, stringBuilder.length());
		stringBuilder.append("SELECT ");
		stringBuilder.append(person);
		stringBuilder.append(" From Price WHERE zone='");
		stringBuilder.append(zone);
		stringBuilder.append("'");
		
		return stringBuilder.toString();
	}
	
	private final BigDecimal retrievePrice(List<BigDecimal> priceList) throws RoutePlannerException {
		if (!priceList.isEmpty()) {
			BigDecimal price;
			price = priceList.get(0);
			return price;
		} else {
			throw new RoutePlannerException("Zone does not exist");
		}		
	}
	
}