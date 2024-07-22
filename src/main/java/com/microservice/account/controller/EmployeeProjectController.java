package com.microservice.account.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.account.dto.ResponseDto;
import com.microservice.account.exception.ResourceNotFoundException;
import com.microservice.account.model.Employee;
import com.microservice.account.model.EmployeeProject;
import com.microservice.account.model.Project;
import com.microservice.account.service.EmployeeProjectService;
import com.microservice.account.service.EmployeeService;
import com.microservice.account.service.ProjectService;

@RestController
public class EmployeeProjectController {

	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private EmployeeProjectService employeeProjectService;
	
	@PostMapping("/api/employee/project/assign/{employeeId}/{projectId}")
	public ResponseEntity<?> assignProjectToEmployee(@PathVariable("employeeId") int employeeId, @PathVariable("projectId") int projectId ) {
		
		Employee employee = null;
		Project project = null;
		
		try {
			employee = employeeService.getEmployeeById(employeeId);
		} catch (ResourceNotFoundException e) {
			
			return ResponseEntity.badRequest().body(new ResponseDto(e.getMessage(), "400") );
		}
		
		try {
			project = projectService.getProjectById(projectId);
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.badRequest().body(new ResponseDto(e.getMessage(), "400"));
		}
		
		EmployeeProject employeeProject = new EmployeeProject();
		employeeProject.setEmployee(employee);
		employeeProject.setProject(project);
		employeeProject.setDateOfAssign(LocalDate.now());
		
		employeeProject = employeeProjectService.postEmployeeProject(employeeProject);
		
		return ResponseEntity.ok().body(employeeProject);
		
	}
}
