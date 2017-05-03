package com.jitesh.JsoupTest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


public class MlaSql {

	
	public static void main(String[] args) throws Exception {

		String user = AppConf.getConfig("db.mysql.username");
		String pass = AppConf.getConfig("db.mysql.password");
		String url = AppConf.getConfig("db.mysql.url");
		String database = AppConf.getConfig("db.mysql.database");
		Connection con = MlaUtilDB.getConnection(user, pass, url, database);
		Statement stmt = con.createStatement();

		String insertString = AppConf.getConfig("inserttbl1");
		String fileName = AppConf
				.getConfig("db.mysql.sampleFile");
		File file = new File(fileName);
		FileReader fr = null;
		BufferedReader br = null;

		try {
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			String line = br.readLine();
			while (line != null) {
			//	line = line.replace("\"", "");
			//	System.out.println(line);
				String[] lines = line.split(";");
				String query = insertString + "(" + Integer.parseInt(lines[0]) + ",'" + lines[1]
						+ "'," + Integer.parseInt(lines[2]) + "," + Double.parseDouble(lines[3]) + "," + Double.parseDouble(lines[4])
						+ "," + Double.parseDouble(lines[5])+")";
				try {
					//System.out.println(query);
					stmt.executeUpdate(query);
				} catch (SQLException e) {
					System.out.println("Problem in DATA ;) "+e.getMessage());
				}
				line = br.readLine();
			}
		System.out.println("Done");
		} catch (FileNotFoundException e1) {
			e1.getStackTrace();
		} catch (IOException e2) {
			System.out.println("File is not readable to this user");
			e2.printStackTrace();
		}finally{
			MlaUtilDB.closeConnection(con);
			MlaUtilDB.closeStmt(stmt);
		}
	}

}
