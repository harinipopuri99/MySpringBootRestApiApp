package com.microservice.account.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	
	/*** 
	 * -- Make this a rest controller
	 * -- connect this method to api call. @GetMapping */
	
	@GetMapping("/api/hello")
	public String sayHello() {
		return "Hello Rest API";
	}

}
