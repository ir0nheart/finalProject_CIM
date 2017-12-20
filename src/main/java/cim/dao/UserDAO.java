package cim.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;
import cim.entity.UserInfo;

@Transactional
public class UserDAO {
	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	public UserInfo getUser(String userName) {
		UserInfo UserInfo = new UserInfo();
		List<?> list = hibernateTemplate.find("FROM UserInfo WHERE username=?",
				userName);
		if(!list.isEmpty()) {
			UserInfo = (UserInfo)list.get(0);
		}
		return UserInfo;
	}
	public List<UserInfo> getActiveUsers(){
		List<?> list = hibernateTemplate.find("FROM UserInfo WHERE active=?",true);
		return (List<UserInfo>)list;
	}

}
