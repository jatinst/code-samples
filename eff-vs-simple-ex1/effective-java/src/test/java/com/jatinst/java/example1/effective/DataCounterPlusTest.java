package com.jatinst.java.example1.effective;

import java.sql.SQLException;

import org.junit.Test;

import com.jatinst.java.example1.effective.dao.DataCounterDao;

public class DataCounterPlusTest {

	@Test
	public void testPrintDataCount() throws SQLException {
		//create the mock Dao for the scenario < THRESHOLD
		DataCounterDao mockDao = new FakeDaoImpl(5);
		DataCounterPlus testCounter = DataCounterPlus.getInstance(mockDao);
		testCounter.printDataCount("foo"); //any database name will do here
		
		//create the mockDAO for the scenario > THRESHOLD
		mockDao = new FakeDaoImpl(100);
		testCounter = DataCounterPlus.getInstance(mockDao);
		testCounter.printDataCount("foo");
	}

}
