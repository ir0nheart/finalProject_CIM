package cim.service;

import java.util.List;

import org.springframework.security.access.annotation.Secured;
import org.springframework.transaction.annotation.Transactional;

import cim.entity.Appointment;
import cim.entity.Maintenance;
import cim.entity.MaintenanceCompany;
import cim.entity.Schedule;

@Secured({"ROLE_MAINTENANCE","ROLE_ADMIN","ROLE_CAROWNER"})
public interface IMaintenanceCompanyService {

	public MaintenanceCompany getDataByUserId(long uid);
	
	public MaintenanceCompany getDataByCompanyId(long companyId);
	
	@Transactional
	void saveOrUpdate(MaintenanceCompany co);
	@Transactional
	void updateMaintenanceCompany(MaintenanceCompany co);
	
	@Secured({"ROLE_ADMIN"})
	List<MaintenanceCompany> getActivationList();
	
	List<MaintenanceCompany> getMaintenanceCompanyList();
	List<Schedule> getCompanySchedule(long companyId);
	
	public Appointment getAppointmentById(long appId);
	
	@Transactional
	public void createMaintenance(Maintenance maintenance);
	
	public Maintenance getMaintenanceById(long maintenanceId);
	
	public List<Maintenance> findMaintenanceCars(long maintenanceCompanyId);
	@Transactional
	public void updateMaintenanceStatus(Maintenance maintenance);
}
