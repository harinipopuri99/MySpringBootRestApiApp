package com.microservice.account.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.account.enums.JobTitle;
import com.microservice.account.enums.RoleType;
import com.microservice.account.model.HR;
import com.microservice.account.model.UserInfo;
import com.microservice.account.service.HRService;
import com.microservice.account.service.UserInfoService;

@RestController
public class HRController {

	@Autowired
	private HRService hrService;
	
	@Autowired
	private UserInfoService userInfoService;
	
	@PostMapping("/api/hr/add")
	public HR postHR(@RequestBody HR hr) {
		hr.setJobTitle(JobTitle.HR);
		/*detach UserInfo from hr */
		UserInfo userInfo = hr.getUserInfo();

		/*assign role and save it in DB and fetch full UserInfo object with id*/
		userInfo.setRole(RoleType.HR);
		userInfo = userInfoService.insertUserInfo(userInfo);

		/* Attach UserInfo back to HR */
		hr.setUserInfo(userInfo);

		/* Save HR */
		return hrService.insertHr(hr);
	}
	
}
