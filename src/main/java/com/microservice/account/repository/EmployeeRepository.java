package com.microservice.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservice.account.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
	

}
