package com.jatinst.java.example1.effective.dao;

import java.sql.SQLException;

public interface DataCounterDao {
	public long getDataCount(String dbName) throws SQLException;
}
