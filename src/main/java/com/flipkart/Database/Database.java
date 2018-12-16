package com.flipkart.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {

	public Connection conn ;
	public Statement stmt ;
	
	public Statement getStatement() {
		
		String driver = "com.mysql.cj.jdbc.driver" ;
		String userName = "root" ;
		String pwd = "123" ;
		try {
			conn = DriverManager.getConnection(driver, userName, pwd);
			stmt = conn.createStatement();
			return stmt ;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return stmt;
	}
	
	
	public ResultSet selectData(String query) throws SQLException {
		ResultSet data = getStatement().executeQuery(query);
		return data ;
	}
	
	public void updateData(String query) throws SQLException {
		getStatement().executeUpdate(query);
	}
	
	public void insertData(String query) throws SQLException {
		getStatement().executeUpdate(query);
	}
	
	public void deleteData(String query) throws SQLException {
		getStatement().executeUpdate(query);
	}
}
