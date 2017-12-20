package cim.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;

import cim.dao.MaintenanceCompanyDAO;
import cim.entity.Appointment;
import cim.entity.Maintenance;
import cim.entity.MaintenanceCompany;
import cim.entity.Schedule;



public class MaintenanceCompanyService implements IMaintenanceCompanyService {
	@Autowired
	private MaintenanceCompanyDAO maintenanceCompanyDAO;
	@Autowired
	private HibernateTemplate hibernateTemplate;
	@Override
	public List<MaintenanceCompany> getActivationList() {
		
		return maintenanceCompanyDAO.getActivationList();
	}
	@Override
	public void saveOrUpdate(MaintenanceCompany co) {
			hibernateTemplate.saveOrUpdate(co);	
		
	}
	@Override
	public void updateMaintenanceCompany(MaintenanceCompany co) {
		if(isMaintenanceCompanyExists(co)) {
			hibernateTemplate.merge(co);
			}
		
	}
	public boolean isMaintenanceCompanyExists(MaintenanceCompany userinfo){
		List<?> list= hibernateTemplate.find("FROM MaintenanceCompany WHERE maintenanceCompanyId=?", userinfo.getMaintenanceCompanyId());
		if(!list.isEmpty()) {
			return true;
		}
		return false;
	}
	@Override
	public MaintenanceCompany getDataByUserId(long uid) {
	
		return maintenanceCompanyDAO.getMaintenanceCompany(uid);
	}
	
	@Override
	public MaintenanceCompany getDataByCompanyId(long companyId) {
	
		return maintenanceCompanyDAO.getMaintenanceCompanyById(companyId);
	}
	@Override
	public List<MaintenanceCompany> getMaintenanceCompanyList() {
		
		return maintenanceCompanyDAO.getMaintenanceCompanyList();
	}
	@Override
	public List<Schedule> getCompanySchedule(long companyId) {
		List<?> list = hibernateTemplate.find("FROM Schedule WHERE maintenanceCompanyId=?", companyId);
		return (List<Schedule>) list;
	}
	@Override
	public Appointment getAppointmentById(long appId) {
		Appointment app = null;
		List<?> list = hibernateTemplate.find("FROM Appointment WHERE appointmentId=?",appId);
		if(!list.isEmpty()) {
			app=(Appointment)list.get(0);
		}
		return app;
	}
	
	public void createMaintenance(Maintenance maintenance) {
		
		hibernateTemplate.saveOrUpdate(maintenance);
	}
	
	public Maintenance getMaintenanceById(long id) {
		Maintenance maintenance = null;
		List<?> list = hibernateTemplate.find("FROM Maintenance WHERE maintenanceId=?",id);
		if(!list.isEmpty()) {
			maintenance = (Maintenance)list.get(0);
		}
		return maintenance;
		
	}
	@Override
	public List<Maintenance> findMaintenanceCars(long maintenanceCompanyId) {
		List<?> list = hibernateTemplate.find("FROM Maintenance WHERE maintenanceCompanyId=?",maintenanceCompanyId);
		return (List<Maintenance>)list;
	}
	
	public void updateMaintenanceStatus(Maintenance maintenance) {
		hibernateTemplate.merge(maintenance);
		
	}
	
	
	}

	

