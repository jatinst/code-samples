package com.jatinst.java.example1.effective.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.apache.log4j.Logger;

public class TradeDaoImpl implements DataCounterDao {
	private static final Logger logger = Logger.getLogger(TradeDaoImpl.class);
	private DataSource tradeDs;

	public TradeDaoImpl(DataSource tradeDs) {
		this.tradeDs = tradeDs;
	}

	@Override
	public long getDataCount(String dbName) throws SQLException{
		long result = 0;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String query = "Select count(*) from " + dbName + ".trade";

		try {
			conn = tradeDs.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				result = rs.getLong(1); // the resultSet only has 1 column
			}
			if (logger.isDebugEnabled())
				logger.debug("Got the count of table Trade = " + result);
			
		} catch (SQLException e) {
			logger.error("Error in running SQL", e);
			throw e;
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					logger.warn("Could not close ResultSet", e);
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					logger.warn("Could not close Statement", e);
				}
			}
			if(conn != null){
				try {
					conn.close();
				} catch (SQLException e) {
					logger.warn("Could not close connection", e);
				}
			}
		}
		return result;
	}

}
