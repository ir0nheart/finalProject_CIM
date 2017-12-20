package cim.service;


import org.springframework.security.access.annotation.Secured;
import org.springframework.transaction.annotation.Transactional;

import cim.entity.UserInfo;


public interface IUserService {
	//@Secured({"ROLE_ADMIN","ROLE_MAINTENANCE","ROLE_INSURANCE","ROLE_CAROWNER"})
	UserInfo getDataByUserName(String userName);
	
	@Secured({"ROLE_ADMIN"})
	public void updateUser(UserInfo uinf);
}
