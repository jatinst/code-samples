package com.jatinst.java.example1.simple;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Class that demonstrates the quick and dirty way of querying a database using a JDBC connection. This uses the
 * DriverManager class to create the database connection as described in
 * http://docs.oracle.com/javase/tutorial/jdbc/basics/connecting.html
 * 
 * This class purposefully does not use log4j or DataSources, those will be used in the effective-java project
 */
public class DataCounter {

	/**
	 * Function that returns a java.sql.connection object using the DriverManager class
	 * 
	 * @param dbUrl
	 *            the URL of the database, in the proper format for the database type
	 * @param dbUser
	 *            the username for the DB connection
	 * @param dbPassword
	 *            the password for the DB connection
	 * @param dbDriver
	 *            the driver for the DB connection
	 * @return connection object for the DB passed by params
	 * @throws SQLException
	 *             if the Connection cannot be established
	 * @throws ClassNotFoundException
	 *             if the Driver class is not found
	 */
	public Connection getConnection(String dbUrl, String dbUser,
			String dbPassword, String dbDriver) throws SQLException,
			ClassNotFoundException {
		Class.forName(dbDriver);
		Connection conn = DriverManager
				.getConnection(dbUrl, dbUser, dbPassword);
		System.out.println("Got Connection to database " + dbUrl);
		return conn;
	}

	/**
	 * Function that prints out the Trade count
	 * 
	 * @param conn
	 *            the DB connection object
	 * @param dbName
	 *            the name of the database schema which has table Trade
	 */
	public void printDataCount(Connection conn, String dbName) {
		long result = 0;

		Statement stmt = null;
		ResultSet rs = null;
		String query = "Select count(*) from " + dbName + ".trade";

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				result = rs.getLong(1); // the resultSet only has 1 column
			}
			System.out.println("Got the count of table Trade = " + result);
		} catch (SQLException e) {
			System.out.println("Error in running SQL");
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					System.out.println("Could not close ResultSet"
							+ e.getMessage());
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					System.out.println("Could not close Statement"
							+ e.getMessage());
				}
			}
		}
	}

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		/*
		 * Using hard-coded values just for demo purposes, replace with the proper values for your database the DDLs to
		 * create the table and insert SQLs can be found in the top level folder data-files for this example
		 */
		String databaseName = "test"; // change to your database schema name
		String dbUrl = "jdbc:mysql://localhost:3306/"; // change to your MySQL database URL
		String dbUser = "testuser"; // change to your MySQL database username
		String dbPassword = "testpassword"; // change to your MySQL database password
		String dbDriver = "com.mysql.jdbc.Driver"; // the MySQL driver, do not change unless using another database type

		//create the DataCounter object
		DataCounter counter = new DataCounter();
		
		//creating the connection that will be used (we throw the exceptions directly here)
		Connection conn = counter.getConnection(dbUrl, dbUser, dbPassword, dbDriver);
		
		//calling the function that prints the count
		counter.printDataCount(conn, databaseName);
	}
}
