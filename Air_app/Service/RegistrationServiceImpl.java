package com.air_app.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

import com.air_app.DBConfiguration;
import com.air_app.security.PasswordEncrypt;

public class RegistrationServiceImpl implements RegistrationService {
	
	@Override
	public String registration(Scanner sc) throws Exception {
		System.out.println("enter the user name");
		String username = sc.next();
		System.out.println("enter the password");
		String password = sc.next();
		
//		 String encryptedPassword = PasswordEncrypt.encrypt(password);
		 Connection con = DBConfiguration.getDbConnection();
		 
//		 PreparedStatement ps;
		 int rows =-1;
		 try {
			 String encryptedPassword = PasswordEncrypt.encrypt(password);
			 PreparedStatement ps=con.prepareStatement("insert into User (username,password) values(?,?)");
			 ps.setString(1,username);
			 ps.setString(2, encryptedPassword);
			 
			rows =ps.executeUpdate();
		 }
		 catch(Exception e) {
			 e.printStackTrace();
		 }
		if(rows>0) {
			return "user registered successfully";
		}
		return "something went wrong";
	}

}
