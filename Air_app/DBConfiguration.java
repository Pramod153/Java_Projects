package com.air_app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
public class DBConfiguration {
	private static final String url = "jdbc:mysql://localhost:3306/air_reservation";
	private static final String username ="root";
	private static final String password ="root";
	private static Connection con= null;
	
	public static final Connection getDbConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			if(con==null) {
				con = DriverManager.getConnection(url,username,password);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	public static void createTable() {
		try {
		String sqlEx ="create table User1(userId int(20),username varchar(30),password varchar(255))";
		Connection con=getDbConnection();
		Statement stmt = con.createStatement();
		boolean execute = stmt.execute(sqlEx);
		if(!execute)System.out.println("table created");
		else System.out.println("something went wrong");
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
}
