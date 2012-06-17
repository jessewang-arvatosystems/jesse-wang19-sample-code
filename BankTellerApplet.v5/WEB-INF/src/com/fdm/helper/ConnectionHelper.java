package com.fdm.helper;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public final class ConnectionHelper {

	public static void closeConnection(ObjectInputStream connection) {
		if (connection != null) {
			try {
				connection.close();
			} catch (IOException e) {}
		}
	}

	public static void closeConnection(ObjectOutputStream connection) {
		if (connection != null) {
			try {
				connection.close();
			} catch (IOException e) {}
		}
	}

	public static void closeConnection(InputStream connection) {
		if (connection != null) {
			try {
				connection.close();
			} catch (IOException e) {}
		}
	}

	public static void closeConnection(PreparedStatement connection) {
		if (connection != null) {
			try {
				connection.close();
			} 
			catch (SQLException e) {}
		}
	}
	
}
