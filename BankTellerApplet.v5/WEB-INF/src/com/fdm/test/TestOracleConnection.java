package com.fdm.test;

import java.sql.Connection;
import java.sql.SQLException;

import oracle.jdbc.pool.OracleDataSource;

import org.junit.Test;

public final class TestOracleConnection {

	private OracleDataSource oracleDataSource;

	@Test
	public void testConnectionOracleDB() {
		try {
			oracleDataSource = new OracleDataSource();
			oracleDataSource.setDriverType("thin");
			oracleDataSource.setServerName("oracle.fdmgroup.com");
			oracleDataSource.setPortNumber(1521);
			oracleDataSource.setDatabaseName("campus");
			oracleDataSource.setUser("jessewang");
			oracleDataSource.setPassword("JW478Ca5");
			
			Connection conn = oracleDataSource.getConnection();
			System.out.println("connected");
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
