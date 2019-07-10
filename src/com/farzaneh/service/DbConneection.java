package com.farzaneh.service;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.farzaneh.exception.ProcessException;

public class DbConneection {
	private static DataSource datasource;
	private Connection connection;

	static {
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			datasource = (DataSource) envContext.lookup("jdbc/postgres");
		} catch (NamingException e) {
			throw new ProcessException("Error Occure SQL connection", e);
		}
	}

	public DbConneection() {
		try {
			openConnection();
		} catch (SQLException e) {
			throw new ProcessException("Error Occure opening SQL connection", e);
		}
	}

	private Connection openConnection() throws SQLException {
		this.connection = datasource.getConnection();
		return connection;
	}

	public Connection getConnectionInstance() {
		return connection;
	}

	public void closeConnection() {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new ProcessException("Error Occure closing SQL connection", e);
			}
		}
	}
}
