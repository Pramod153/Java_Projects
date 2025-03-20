package com.hibernate.quizapp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		private int ID;
		private String User_name;
		private String Password;
		public User() {
			super();
			// TODO Auto-generated constructor stub
		}
		public int getID() {
			return ID;
		}
		public void setID(int iD) {
			ID = iD;
		}
		public String getUser_name() {
			return User_name;
		}
		public void setUser_name(String user_name) {
			User_name = user_name;
		}
		public String getPassword() {
			return Password;
		}
		public void setPassword(String password) {
			Password = password;
		}
		
	
}
