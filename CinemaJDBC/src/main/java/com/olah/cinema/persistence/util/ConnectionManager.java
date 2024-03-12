package com.olah.cinema.persistence.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class ConnectionManager {
	private static final String URL = "jdbc:h2:./src/main/resources/cinema_db";
	private ConnectionManager() {
	}
	public static Connection getConnection(){
		try {
			return DriverManager.getConnection(URL);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
