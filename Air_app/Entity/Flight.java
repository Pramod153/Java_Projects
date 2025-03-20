package com.air_app.entity;

import java.util.Date;

public class Flight {

	private String flightId;
	private String flight_src;
	private String flight_des;
	private int availableSeats;
	private Date dateOfjourney;
	

	public Flight(String flightId, String flight_src, String flight_des, int availableSeats, Date dateOfjourney) {
		super();
		this.flightId = flightId;
		this.flight_src = flight_src;
		this.flight_des = flight_des;
		this.availableSeats = availableSeats;
		this.dateOfjourney = dateOfjourney;
	}
	public String getFlightId() {
		return flightId;
	}
	public void setFlightId(String flightId) {
		this.flightId = flightId;
	}
	public String getFlight_src() {
		return flight_src;
	}
	public void setFlight_src(String flight_src) {
		this.flight_src = flight_src;
	}
	public String getFlight_des() {
		return flight_des;
	}
	public void setFlight_des(String flight_des) {
		this.flight_des = flight_des;
	}
	public int getAvailableSeats() {
		return availableSeats;
	}
	public void setAvailableSeats(int availableSeats) {
		this.availableSeats = availableSeats;
	}
	public Date getDateOfjourney() {
		return dateOfjourney;
	}
	public void setDateOfjourney(Date dateOfjourney) {
		this.dateOfjourney = dateOfjourney;
	}


	

}
