////package com.air_app.service;
////
////import java.sql.Connection;
////import java.sql.PreparedStatement;
////import java.sql.ResultSet;
////import java.util.ArrayList;
////import java.util.Date;
////import java.util.List;
////import java.util.Scanner;
////
////import com.air_app.DBConfiguration;
////import com.air_app.entity.Flight;
////import com.mysql.cj.xdevapi.Result;
////
////public class FlightServiceImpl implements FlightService {
////	Connection con = DBConfiguration.getConection();
////	Flight flight;
////	List<Flight> list = new ArrayList<>();
////	
////	@Override
////	public void searchEngine(Scanner sc, String username) {
////		// TODO Auto-generated method stub
////		System.out.println("welcome"+username);
////		try {
////			System.out.println("enter the source");
////			String src= sc.next();
////			System.out.println("enter the destination");
////			String dest= sc.next();
////			System.out.println("enter the date of journeysource");
////			String journeyDate= sc.next();
////			PreparedStatement ps = con.prepareStatement("selct* from where source =? and destination =? and dateOfFlight like ? ");
////			ps.setString(1, src);
////			ps.setString(2, dest);
////			ps.setString(3, journeyDate);
////			
////			ResultSet rs = ps.executeQuery();
////			 while(rs.next()) {
////		        	String flightId = rs.getString(1);
////		        	String source = rs.getString(2);
////		        	String destination = rs.getString(3);
////		        	int seats = rs.getInt(4);
////		        	Date date = rs.getDate(5);
////		        	
////		        	flight = new Flight(flightId,source,destination,seats,date);
////		        	list.add(flight);
////		        }
////			
////			if(!list.isEmpty()) {
////				System.out.println("avialable flights:");
////				System.out.println("         ");
////				System.out.println(list);
////				
////				System.out.println("enter the flight id to proceed");
////				String flightId = sc.next();
////				for(Flight flight:list) {
////					if(flightId.equals(flight.getFlightId())) {
////						bookingTicket(sc,username,flightId,flight.getFlight_src(),flight.getFlight_des(),flight.getAvailableSeats(),flight.getDateOfjourney());
////						return;
////					}
////				}
////			}else {
////				System.out.println("no flight found");
////				return;
////			}
////		}catch(Exception e) {
////			e.printStackTrace();
////		}
////	}
////
////	private void bookingTicket(Scanner sc, String username, String flightId, String flight_src, String flight_des,
////			int availableSeats, Date dateOfjourney) {
////		try {
////			System.out.println("enter the number of seats want to book");
////			int requestedSeats = sc.nextInt();
////			if(requestedSeats<=availableSeats) {
////				availableSeats -= requestedSeats;
////				PreparedStatement ps = con.prepareStatement("update flights set availableSeats=? where flightId = ?");
////			    ps.setInt(1, availableSeats);  
////			    ps.setString(2, flightId);
////			    int row = ps.executeUpdate();
////			    if(row>0) {
////			    	System.out.println("Ticket booked successfully");
////			    	System.out.println("Initiating to print boarding pass.Please wait...");
////			    	Thread.sleep(5000);
////			    	printBoardingPass(username,flightId, flight_src,flight_des,dateOfjourney);
////			    }
////			
////			}
////			}catch(Exception e) {
////				e.printStackTrace();
////			}
////			
////		}
////
////
////		private void printBoardingPass(String username, String flightId, String flight_src, String flight_des,
////				Date dateOfJourney) {
////			System.out.println("your boarding pass:");
////			System.out.println("Passenger Name :"+username);
////	        System.out.println("FlightID       :"+flightId);
////	        System.out.println("Source         :"+flight_src);
////	        System.out.println("Destination    :"+flight_des);
////	        System.out.println("Date Of Journey:"+dateOfJourney);
////	        
////	        System.out.println("Happy Journey.Have a safe flight.");
////		}
////
////		
////	
////			
////		
////		
////	}
////
////	
////
////	
////
package com.air_app.service;

import java.sql.Connection;
import java.sql.Date; // Import this for MySQL date conversion
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.air_app.DBConfiguration;
import com.air_app.entity.Flight;

public class FlightServiceImpl implements FlightService {
    Connection con = DBConfiguration.getDbConnection();
    Flight flight;
    List<Flight> list = new ArrayList<>();

    @Override
    public void searchEngine(Scanner sc, String username) {
        System.out.println("Welcome " + username);
        try {
            System.out.println("Enter the source:");
            String src = sc.next();
            System.out.println("Enter the destination:");
            String dest = sc.next();
            System.out.println("Enter the date of journey (YYYY-MM-DD):");
            String journeyDate = sc.next();
            
            // Convert user input date to MySQL date format
            Date sqlDate = Date.valueOf(journeyDate);

            PreparedStatement ps = con.prepareStatement("SELECT * FROM flights WHERE source=? AND destination=? AND dateOfFlight=?");
            ps.setString(1, src);
            ps.setString(2, dest);
            ps.setDate(3, sqlDate); // Use setDate() instead of setString()

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String flightId = rs.getString(1);
                String source = rs.getString(2);
                String destination = rs.getString(3);
                int seats = rs.getInt(4);
                Date date = rs.getDate(5);

                flight = new Flight(flightId, source, destination, seats, date);
                list.add(flight);
            }

            if (!list.isEmpty()) {
                System.out.println("Available flights:");
                System.out.println("_____________");
                for (Flight f : list) {
                    System.out.println("FlightID: " + f.getFlightId() + " | Source: " + f.getFlight_src() +
                                       " | Destination: " + f.getFlight_des() + " | Available Seats: " + f.getAvailableSeats() +
                                       " | Date: " + f.getDateOfjourney());
                }

                System.out.println("Enter the flightId to proceed:");
                String flightId = sc.next();
                for (Flight flight : list) {
                    if (flightId.equals(flight.getFlightId())) {
                        bookingTicket(sc, username, flightId, flight.getFlight_src(), flight.getFlight_des(), flight.getAvailableSeats(), flight.getDateOfjourney());
                        return;
                    }
                }
            } else {
                System.out.println("No flights found for the given criteria.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void bookingTicket(Scanner sc, String username, String flightId, String flight_src, String flight_des,
                               int availableSeats, java.util.Date date) {
        try {
            System.out.println("Enter the number of seats you want to book:");
            int requestedSeats = sc.nextInt();

            // Check if requested seats are available
            if (requestedSeats > availableSeats) {
                System.out.println("Not enough seats available!");
                return;
            }

            availableSeats -= requestedSeats;
            PreparedStatement ps = con.prepareStatement("UPDATE flights SET availableSeats=? WHERE flightId=?");
            ps.setInt(1, availableSeats);
            ps.setString(2, flightId);
            int row = ps.executeUpdate();

            if (row > 0) {
                System.out.println("Ticket booked successfully.");
                System.out.println("Generating boarding pass... Please wait.");
                Thread.sleep(3000);
                printBoardingPass(username, flightId, flight_src, flight_des, (Date) date);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void printBoardingPass(String username, String flightId, String flight_src, String flight_des,
                                   Date dateOfJourney) {
        System.out.println("\n======== YOUR BOARDING PASS ========");
        System.out.println("Passenger Name : " + username);
        System.out.println("Flight ID      : " + flightId);
        System.out.println("Source         : " + flight_src);
        System.out.println("Destination    : " + flight_des);
        System.out.println("Date Of Journey: " + dateOfJourney);
        System.out.println("====================================");
        System.out.println("Happy Journey! Have a safe flight.\n");
    }

	

}