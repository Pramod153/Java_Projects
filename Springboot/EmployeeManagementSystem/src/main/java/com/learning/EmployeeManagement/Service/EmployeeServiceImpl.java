package com.learning.EmployeeManagement.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.EmployeeManagement.Entity.Employee;
import com.learning.EmployeeManagement.Payload.EmployeeDto;
import com.learning.EmployeeManagement.Respository.EmployeeRepository;
import com.learning.EmployeeManagement.exception.ResourceNotFoundException;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private EmployeeRepository repo;
	
	@Override
	public EmployeeDto addEmployee(EmployeeDto employeeDto) {
		Employee emp = this.mapper.map(employeeDto, Employee.class);
		Employee savedemp = this.repo.save(emp);
		return this.mapper.map(savedemp, EmployeeDto.class);
	}

	@Override
	public List<EmployeeDto> getEmployee() {
		List<Employee> list =this.repo.findAll();
		return list.stream()
				.map(emp->this.mapper.map(emp, EmployeeDto.class))
				.collect(Collectors.toList());
	}
	
	public EmployeeDto getEmployeeById(int id) {
//		Employee employee = this.repo.findById(id).get();
//		return this.mapper.map(employee,EmployeeDto.class);
		Employee employee = this.repo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Employee","empId",id));
		return this.mapper.map(employee,EmployeeDto.class);
	}

	@Override
	public EmployeeDto updateEmployee(int id, EmployeeDto empDto) {
		Employee emp = this.repo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Employee","empId",id));
		emp.setEmpName(empDto.getEmpName());
		emp.setDeptName(empDto.getDeptName());
		Employee UpdateEmployee = this.repo.save(emp);
		return this.mapper.map(UpdateEmployee ,EmployeeDto.class);
	}

	@Override
	public void deleteEmployee(int id) {
		Employee emp = this.repo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Employee","empId",id));
		this.repo.delete(emp);
		
	}
//	@Override
//	public void deleteEmp(int id) {
//		Employee emp = this.repo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Employee","empId",id));
//		this.repo.delete(emp);
//	}
	
}
