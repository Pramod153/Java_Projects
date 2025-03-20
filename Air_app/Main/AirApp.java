package com.air_app;

import java.util.Scanner;

import com.air_app.Payload.LoginResponse;
import com.air_app.service.FlightService;
import com.air_app.service.FlightServiceImpl;
import com.air_app.service.LoginService;
import com.air_app.service.LoginServiceImpl;
import com.air_app.service.RegistrationService;
import com.air_app.service.RegistrationServiceImpl;

public class AirApp {
	
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		RegistrationService rs = new RegistrationServiceImpl();
		LoginService ls = new LoginServiceImpl();
		FlightService flightService = new FlightServiceImpl();
		
		System.out.println("welcome to airapp");
		Thread.sleep(2000);
		System.out.println("starting the app server.Please wait...");
		Thread.sleep(5000);
		
		char res;
		do {
			System.out.println("select option:");
			System.out.println("1.newuser 2.already a member");
			int option= sc.nextInt();
			switch(option) {
			case 1: String registrationResponse = rs.registration(sc);
					System.out.println(registrationResponse);
					break;
			case 2: LoginResponse loginResponse = ls.login(sc);
						if(loginResponse.isLoggedIn()) {
						System.out.println("login successfull");
						flightService.searchEngine(sc,loginResponse.getUsername());
					}else {
						System.out.println("login failed");
					}
					break;
					
			default : System.out.println("invalid selection");
			}
			System.out.println("do you want to continue:y/n");
			res= sc.next().toLowerCase().charAt(0);
		}while(res=='y');
		System.out.println("thanks for using airapp");
	}

}
