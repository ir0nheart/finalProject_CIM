package cim.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import cim.dao.UserDAO;
import cim.entity.UserInfo;



public class UserService implements IUserService {
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	public UserInfo getDataByUserName(String userName) {
		return userDAO.getUser(userName);
	}
	
	@Transactional
	public void updateUser(UserInfo uinf) {
		if(isUserExists(uinf)) {
			hibernateTemplate.merge(uinf);
			}
	}
	public boolean isUserExists(UserInfo userinfo){
		List<?> list= hibernateTemplate.find("FROM UserInfo WHERE username=?", userinfo.getUsername());
		if(!list.isEmpty()) {
			return true;
		}
		return false;
	}
}
