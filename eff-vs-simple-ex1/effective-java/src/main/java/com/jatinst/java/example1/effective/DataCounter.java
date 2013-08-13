package com.jatinst.java.example1.effective;

import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.jatinst.java.example1.effective.dao.DataCounterDao;
import com.jatinst.java.example1.effective.dao.TradeDaoImpl;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

/**
 * Class that uses effective java paradigms to solve the same problem as shown in the simple-java project
 *
 * * Uses a static factory method
 * * Uses a Data Abstraction Object (DAO) to wrap the database call
 * * The DAO is passed to the DataCounter constructor as an argument (dependency injection)
 * * Uses DataSource instead of simple JDBC connection 
 * 		(Note that the connection is not closed in simple-java by any function!)
 * * Uses Logger instead of System.out.println
 * 
 * NOTE - move the log4j.properties file into src/main/resources (or your classpath) before running this class, else
 * the logs will not print
 */
public class DataCounter {
	private static final Logger logger = Logger.getLogger(DataCounter.class);
	
	private final DataCounterDao counterDao; //private member DAO
	
	/**
	 * Private constructor to prevent inheritance
	 * @param counterDao the DAO 
	 */
	private DataCounter(DataCounterDao counterDao){
		this.counterDao = counterDao;
	}
	
	/**
	 * Static factory method that returns a new object each time
	 * @param counterDao the DAO
	 * @return an object of class DataCounter
	 */
	public static DataCounter getInstance(DataCounterDao counterDao){
		return new DataCounter(counterDao);
	}
	
	public void printDataCount(String dbName) throws SQLException{
		long count = counterDao.getDataCount(dbName);
		logger.info("Got the count of table Trade = " + count);
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
		//we do not need the driver in this case
		//String dbDriver = "com.mysql.jdbc.Driver"; // the MySQL driver, do not change unless using another database type

		//We first create the DataSource:
		MysqlDataSource dataSource = new MysqlDataSource();
		dataSource.setUser(dbUser);
		dataSource.setPassword(dbPassword);
		dataSource.setUrl(dbUrl);
		
		//create a concrete implementation of DataCounterDao
		DataCounterDao tradeDao = new TradeDaoImpl(dataSource);
		
		//create an object of type DataCounter using the static factory methods
		DataCounter counter = DataCounter.getInstance(tradeDao);
		
		//calling the function that prints the count
		counter.printDataCount(databaseName);
	}
	
}
