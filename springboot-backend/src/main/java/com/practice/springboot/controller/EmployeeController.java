package com.practice.springboot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practice.springboot.exception.ResourceNotFoundException;
import com.practice.springboot.model.Employee;
import com.practice.springboot.repository.EmployeeRepository;

@RestController
@RequestMapping("/emp")
@CrossOrigin(origins = "http://localhost:4200")
public class EmployeeController {
	
	@Autowired
	private EmployeeRepository employeeRepository ; //this repo contain the employee
	
	
	//get all the employee details rest api
	@GetMapping("/allEmployee")
	public List<Employee> getAllEmployees(){
		
		return employeeRepository.findAll();
	}
	
	
	//use to add the employee rest api
	@PostMapping("/addEmployee")
	public Employee createEmployee(@RequestBody Employee employee) {
		return employeeRepository.save(employee);
	}
	
	//get employee by id rest api
	@GetMapping("/getById/{Id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Long Id) {
		
		Employee employee = employeeRepository.findById(Id).
				orElseThrow( ()-> new ResourceNotFoundException("Employee not exist" ));
		return ResponseEntity.ok(employee);
		
	}
	
	//update employee rest api
	//we will retrive the employee by id from data base and after that employee will be
	// updated from the client
	
	@PutMapping("/updateEmployee/{Id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable Long Id,@RequestBody Employee employeeDetails){
		
		//get employee by id
		Employee employee = employeeRepository.findById(Id).
				orElseThrow( ()-> new ResourceNotFoundException("Employee not exist" ));
		
		employee.setFirstName(employeeDetails.getFirstName());
		employee.setLastName(employeeDetails.getLastName());
		employee.setEmailId(employeeDetails.getEmailId());
		
		Employee emp =  employeeRepository.save(employee);
		
		return ResponseEntity.ok(emp);	
	}
	
	//delete employee rest api by id
	@DeleteMapping("/deleteEmployee/{Id}")
	public ResponseEntity<Map<String,Boolean>> deleteEmployee(@PathVariable Long Id){
		//get employee by id first
		Employee employee = employeeRepository.findById(Id).
				orElseThrow( ()-> new ResourceNotFoundException("Employee not exist" ));
		
		//now delete from repository
		employeeRepository.delete(employee);
		
		Map<String,Boolean> ans = new HashMap<>();
		ans.put("employee deleted", Boolean.TRUE);
		
		return ResponseEntity.ok(ans);
	}

	
		
}
