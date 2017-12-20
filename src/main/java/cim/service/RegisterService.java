package cim.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.orm.hibernate5.HibernateTemplate;
import cim.entity.CarOwner;
import cim.entity.InsuranceCompany;
import cim.entity.MaintenanceCompany;
import cim.entity.UserInfo;

public class RegisterService {
	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	public boolean register(UserInfo newUserInfo){
	       if(!isUserExists(newUserInfo)) {
	    	   if(adminCheck()) {
	    		   newUserInfo.setRole("ROLE_ADMIN");
	    		   newUserInfo.setActive(true);
	    	   		hibernateTemplate.saveOrUpdate(newUserInfo);}
	       if(newUserInfo.getRole().equals("ROLE_CAROWNER")) {
	    	   CarOwner co = new CarOwner();
	    	   co.setUserinfo(newUserInfo);
	    	   hibernateTemplate.saveOrUpdate(newUserInfo);
	    	   hibernateTemplate.saveOrUpdate(co);
	       }
	       if(newUserInfo.getRole().equals("ROLE_INSURANCE")) {
	    	   InsuranceCompany ic = new InsuranceCompany();
	    	   ic.setUserinfo(newUserInfo);
	    	   hibernateTemplate.saveOrUpdate(newUserInfo);
	    	   hibernateTemplate.saveOrUpdate(ic);
	       }
	       if(newUserInfo.getRole().equals("ROLE_MAINTENANCE")) {
	    	   MaintenanceCompany mc = new MaintenanceCompany ();
	    	   mc.setUserinfo(newUserInfo);
	    	   hibernateTemplate.saveOrUpdate(newUserInfo);
	    	   hibernateTemplate.saveOrUpdate(mc);
	       }
	       return true;
	       }
	       return false;
	    
	}
	
	public boolean isUserExists(UserInfo userinfo){
		List<?> list= hibernateTemplate.find("FROM UserInfo WHERE username=?", userinfo.getUsername());
		if(!list.isEmpty()) {
			return true;
		}
		return false;
	}
	public boolean adminCheck() {
		List<?> list= hibernateTemplate.find("FROM UserInfo");
		if(list.isEmpty()) {
			return true;
		}
		return false;
	}
}
