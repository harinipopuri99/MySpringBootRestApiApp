package com.microservice.account.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.account.enums.JobTitle;
import com.microservice.account.enums.RoleType;
import com.microservice.account.model.Manager;
import com.microservice.account.model.UserInfo;
import com.microservice.account.service.ManagerService;
import com.microservice.account.service.UserInfoService;

@RestController
public class ManagerController {

	@Autowired
	private ManagerService managerService;
	
	@Autowired
	private UserInfoService userInfoService;
	
	@PostMapping("/api/manager/add")
	public Manager addmanager(@RequestBody Manager manager) {
		manager.setJobTitle(JobTitle.PROJECT_MANAGER);
		/*detach UserInfo from hr */
		UserInfo userInfo = manager.getUserInfo();

		/*assign role and save it in DB and fetch full UserInfo object with id*/
		userInfo.setRole(RoleType.MANAGER);
		userInfo = userInfoService.insertUserInfo(userInfo);

		/* Attach UserInfo back to HR */
		manager.setUserInfo(userInfo);

		/* Save HR */
		return managerService.insertManager(manager);
	
	}
}
