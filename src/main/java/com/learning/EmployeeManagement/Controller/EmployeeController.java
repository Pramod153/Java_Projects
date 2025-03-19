package com.learning.EmployeeManagement.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.EmployeeManagement.Payload.APIResponse;
import com.learning.EmployeeManagement.Payload.EmployeeDto;
import com.learning.EmployeeManagement.Service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeController {
		
		@Autowired
		private EmployeeService service;
		
		@PostMapping("/emp")
		public ResponseEntity<EmployeeDto> addEmployee(@RequestBody EmployeeDto empDto){
			EmployeeDto employee = this.service.addEmployee(empDto);
			return new ResponseEntity<>(employee,HttpStatus.CREATED);
			
		}
		@GetMapping("/emp")
		public ResponseEntity<List<EmployeeDto>> getEmployee(){
			List<EmployeeDto> employee = this.service.getEmployee();
			return new ResponseEntity<>(employee,HttpStatus.OK);
		}
		@GetMapping("/emp/{id}")
		public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable int id){
			EmployeeDto employeeById = this.service.getEmployeeById(id);
			return new ResponseEntity<>(employeeById,HttpStatus.FOUND);
		}
		
		@PutMapping("/emp/{id}")
		public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable int id,@RequestBody EmployeeDto empDto ){
			EmployeeDto updateEmployee = this.service.updateEmployee(id,empDto);
			return new ResponseEntity<>(updateEmployee,HttpStatus.OK);
		}
		@DeleteMapping("/emp/{id}")
		public ResponseEntity<APIResponse> deleteEmployee(@PathVariable int id){
			this.service.deleteEmployee(id);
			return new ResponseEntity<>(new APIResponse("emp deleted successfully",true),HttpStatus.OK);
		}
		
}
