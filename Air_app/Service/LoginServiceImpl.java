//package com.air_app.service;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.util.Scanner;
//
//import com.air_app.DBConfiguration;
//import com.air_app.Payload.LoginResponse;
//import com.air_app.security.PasswordEncrypt;
//
//public class LoginServiceImpl  implements LoginService{
//	Connection con=DBConfiguration.getDbConnection();
//	LoginResponse loginResponse = new LoginResponse();
//
//	@Override
//	public LoginResponse login(Scanner sc) {
//		try {
//			System.out.println("enter the username.");
//			String username= sc.next();
//			
//			System.out.println("enter your password");
//			String pass = sc.next();
//			
//			PreparedStatement ps=con.prepareStatement("select * from User where username=? and password=? ");
//			 ps.setString(1,username);
//			 
//			 ResultSet rs = ps.executeQuery();
//			 String savedPassword = null;
//			 
//			 if(rs.next()) {
//				 savedPassword= rs.getString(3);
//			 }
//			 String decrypt = PasswordEncrypt.decrypt(savedPassword);
//			 if(pass.equals(decrypt)) {
//				 loginResponse.setUsername(username);
//				 loginResponse.setLoggedIn(true);
//			 }else {
//				 loginResponse.setUsername(null);
//				 loginResponse.setLoggedIn(false);
//			 }
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
//		return loginResponse;
//	}
//	
//
//	
//
//}
package com.air_app.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import com.air_app.DBConfiguration;
import com.air_app.Payload.LoginResponse;
import com.air_app.security.PasswordEncrypt;

public class LoginServiceImpl  implements LoginService {
    Connection con = DBConfiguration.getDbConnection();
    LoginResponse loginResponse = new LoginResponse();

    @Override
    public LoginResponse login(Scanner sc) {
        try {
            System.out.println("Enter the username:");
            String uname = sc.next();

            System.out.println("Enter your password:");
            String pass = sc.next();

            // ✅ Fix: Correct SQL query (Removed ' ' around ?)
            PreparedStatement ps = con.prepareStatement("SELECT password FROM User WHERE username = ?");
            ps.setString(1, uname);
            ResultSet rs = ps.executeQuery();

            String savedPassword = null;
            if (rs.next()) {
                savedPassword = rs.getString("password"); // ✅ Fetch encrypted password
            }

            if (savedPassword == null) {
                System.out.println("Invalid username or password.");
                loginResponse.setUsername(null);
                loginResponse.setLoggedIn(false);
                return loginResponse;
            }

            // ✅ Decrypt the password before checking
            String decryptedPassword = PasswordEncrypt.decrypt(savedPassword);
            if (pass.equals(decryptedPassword)) {
                loginResponse.setUsername(uname);
                loginResponse.setLoggedIn(true);
                System.out.println("Login successful.");
            } else {
                System.out.println("Incorrect password.");
                loginResponse.setUsername(null);
                loginResponse.setLoggedIn(false);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return loginResponse;
    }
}

