package com.microservice.account.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.account.model.UserInfo;
import com.microservice.account.service.UserInfoService;

@RestController
public class AuthController {

	@Autowired
	private UserInfoService userInfoService;
	
	@GetMapping("api/login")
	public UserInfo login (Principal principal) { //DI - userinfo given is correct.
		
		//Ask spring to share username of loggedin user with us.
		String username = principal.getName();
		
		//Go to DB and fetch userInfo based on email and send it as response
		UserInfo userInfo = (UserInfo) userInfoService.loadUserByUsername(username);
		
		return userInfo;
		
	}
}