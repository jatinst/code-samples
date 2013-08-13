package com.jatinst.java.example1.effective;

import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.jatinst.java.example1.effective.dao.DataCounterDao;
import com.jatinst.java.example1.effective.dao.TradeDaoImpl;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

/**
 * Class that shows how a feature request such as "printing out a different line if the datacount is greater than a
 * certain threshold" is easier to add to code written in effective java
 * 
 * NOTE - move the log4j.properties file into src/main/resources (or your classpath) before running this class, else the logs will not print
 */
public class DataCounterPlus {
	private static final Logger logger = Logger
			.getLogger(DataCounterPlus.class);

	private static final long THRESHOLD = 10;

	private final DataCounterDao counterDao; // private member DAO

	/**
	 * Private constructor to prevent inheritance
	 * 
	 * @param counterDao
	 *            the DAO
	 */
	private DataCounterPlus(DataCounterDao counterDao) {
		this.counterDao = counterDao;
	}

	/**
	 * Static factory method that returns a new object each time
	 * 
	 * @param counterDao
	 *            the DAO
	 * @return an object of class DataCounter
	 */
	public static DataCounterPlus getInstance(DataCounterDao counterDao) {
		return new DataCounterPlus(counterDao);
	}

	public void printDataCount(String dbName) throws SQLException {
		long count = counterDao.getDataCount(dbName);
		logger.info("Got the count of table Trade = " + count);
		if(count > THRESHOLD){
			logger.info("We have more Trades than usual!");
		} else {
			logger.info("We have the same number of Trades as always");
		}
	}

	public static void main(String[] args) throws SQLException,
			ClassNotFoundException {
		/*
		 * Using hard-coded values just for demo purposes, replace with the proper values for your database the DDLs to
		 * create the table and insert SQLs can be found in the top level folder data-files for this example
		 */
		String databaseName = "test"; // change to your database schema name
		String dbUrl = "jdbc:mysql://localhost:3306/"; // change to your MySQL database URL
		String dbUser = "testuser"; // change to your MySQL database username
		String dbPassword = "testpassword"; // change to your MySQL database password
		// we do not need the driver in this case
		// String dbDriver = "com.mysql.jdbc.Driver"; // the MySQL driver, do not change unless using another database
		// type

		// We first create the DataSource:
		MysqlDataSource dataSource = new MysqlDataSource();
		dataSource.setUser(dbUser);
		dataSource.setPassword(dbPassword);
		dataSource.setUrl(dbUrl);

		// create a concrete implementation of DataCounterDao
		DataCounterDao tradeDao = new TradeDaoImpl(dataSource);

		// create an object of type DataCounter using the static factory methods
		DataCounterPlus counter = DataCounterPlus.getInstance(tradeDao);

		// calling the function that prints the count
		counter.printDataCount(databaseName);
	}

}
