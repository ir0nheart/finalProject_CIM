package cim.dao;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import cim.entity.MaintenanceCompany;
import cim.entity.UserInfo;



@Transactional
public class MaintenanceCompanyDAO {
	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	public List<MaintenanceCompany> getActivationList(){
		List<?> list = hibernateTemplate.find("FROM MaintenanceCompany WHERE requestActivate=?", true);
		return (List<MaintenanceCompany>) list;
	}
	
	public MaintenanceCompany getMaintenanceCompany(long userid) {
		MaintenanceCompany maintenanceCompany = new MaintenanceCompany();
		List<?> list = hibernateTemplate.find("FROM MaintenanceCompany WHERE userid=?",
				userid);
		if(!list.isEmpty()) {
			maintenanceCompany = (MaintenanceCompany)list.get(0);
		}
		return maintenanceCompany;
	}
	public MaintenanceCompany getMaintenanceCompanyById(long companyId) {
		MaintenanceCompany maintenanceCompany = new MaintenanceCompany();
		List<?> list = hibernateTemplate.find("FROM MaintenanceCompany WHERE maintenanceCompanyId=?",
				companyId);
		if(!list.isEmpty()) {
			maintenanceCompany = (MaintenanceCompany)list.get(0);
		}
		return maintenanceCompany;
	}
	
	public List<MaintenanceCompany> getMaintenanceCompanyList(){
		List<?> list = hibernateTemplate.find("FROM MaintenanceCompany WHERE requestActivate=?", true);
		List<MaintenanceCompany> tempList= (List<MaintenanceCompany>) list;
		Iterator<MaintenanceCompany> iter = tempList.iterator();
		
		while(iter.hasNext()) {
			MaintenanceCompany mc = iter.next();
			UserInfo UserInfo = new UserInfo();
			List<?> listUser = hibernateTemplate.find("FROM UserInfo WHERE username=?",
					mc.getUserinfo().getUsername());
			if(!listUser.isEmpty()) {
				UserInfo = (UserInfo)listUser.get(0);
			}
			if(!(UserInfo.isActive())) {
		        iter.remove();
		        }
		}
		return tempList;
	}
	
	
}
