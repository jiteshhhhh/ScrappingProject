package com.jitesh.JsoupTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;



public class MlaUtilDB {
	
	public static Connection getConnection(String user, String password, String url, String database){
		Connection con = null;
		try {
			String driver = AppConf.getConfig("db.mysql.driver");
			Class.forName(driver);
			con = DriverManager.getConnection(url+"/"+database, user, password);
		} catch (ClassNotFoundException e) {
			System.err.println("Driver Class is incorrect: "+ e.getMessage());
		} catch (SQLException e) {
			System.err.println("Connection Establish failed. Please check your connection URL and/or credentials: "+e.getMessage());
		}catch (Exception e){
			e.printStackTrace();
		}
		return con;
	}
	
	public static void closeConnection(Connection con){
		try {
			if(null!=con){
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void closeStmt(Statement stmt){
		try {
			if(null!=stmt){
				stmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
