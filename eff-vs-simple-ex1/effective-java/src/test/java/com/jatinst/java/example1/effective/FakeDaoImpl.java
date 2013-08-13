package com.jatinst.java.example1.effective;

import java.sql.SQLException;

import com.jatinst.java.example1.effective.dao.DataCounterDao;

public class FakeDaoImpl implements DataCounterDao {
	private long returnValue;
	
	public FakeDaoImpl (long returnValue){
		this.returnValue = returnValue;
	}

	@Override
	public long getDataCount(String dbName) throws SQLException {
		return returnValue;
	}
	
}
