package com.learning.EmployeeManagement.Service;

import java.util.List;

import com.learning.EmployeeManagement.Payload.EmployeeDto;

public interface EmployeeService {
	EmployeeDto addEmployee(EmployeeDto employeeDto);
	List<EmployeeDto> getEmployee();
	EmployeeDto getEmployeeById(int id);
	EmployeeDto updateEmployee(int id,EmployeeDto empDto);
	void deleteEmployee(int id);
	
}
