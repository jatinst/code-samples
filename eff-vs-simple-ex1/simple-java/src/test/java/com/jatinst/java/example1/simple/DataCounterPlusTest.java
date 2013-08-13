package com.jatinst.java.example1.simple;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;

/**
 * The only way to test such an implementation is to actually connect to the database
 * in the Junit test, so the test function looks very much like the main function of
 * the DataCounterPlus class
 * 
 * Also, note that to test the threshold condition, the only option is to add more rows
 * to the Trade table in the database. This is NOT always possible in real world scenarios
 */
public class DataCounterPlusTest {


	@Test
	public void testPrintDataCount() throws SQLException, ClassNotFoundException {
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
		DataCounterPlus counter = new DataCounterPlus();
		
		//creating the connection that will be used (we throw the exceptions directly here)
		Connection conn = counter.getConnection(dbUrl, dbUser, dbPassword, dbDriver);
		
		//calling the function that prints the count
		counter.printDataCount(conn, databaseName);
		
		//the way this class is implemented, there is nothing we can "assert" as Junit here!
	}

}
