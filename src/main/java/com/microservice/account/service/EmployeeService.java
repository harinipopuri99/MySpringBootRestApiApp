package com.microservice.account.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.microservice.account.exception.ResourceNotFoundException;
import com.microservice.account.model.Employee;
import com.microservice.account.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder; 
	
	public Employee addEmployee(Employee employee) {
		String rawPass = employee.getUserInfo().getPassword();
		String encodedPass = passwordEncoder.encode(rawPass);
		employee.getUserInfo().setPassword(encodedPass);
		return employeeRepository.save(employee);
	}

	public List<Employee> getAllEmployee() {
		// TODO Auto-generated method stub
		return employeeRepository.findAll();
	}

	public void deleteEmployee(int id) {
		// TODO Auto-generated method stub
		employeeRepository.deleteById(id);
	}

	public Employee getEmployeeById(int id) throws ResourceNotFoundException {
		
		 Optional<Employee> optional = employeeRepository.findById(id);
		 if(optional.isEmpty()) {
			 throw new ResourceNotFoundException("Invalid Employee Id Given");
		 }
		
		return optional.get();
	}

}