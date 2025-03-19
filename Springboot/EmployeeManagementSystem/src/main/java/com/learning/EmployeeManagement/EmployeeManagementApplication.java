package com.learning.EmployeeManagement;

import org.modelmapper.ModelMapper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EmployeeManagementApplication {

	public static void main(String[] args) {
		System.out.println("hello springboot");
		SpringApplication.run(EmployeeManagementApplication.class, args);
	}
	
	@Bean
	public ModelMapper modelMapper() {
		return  new ModelMapper();
		
	}

}


