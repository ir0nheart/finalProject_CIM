package cim.service;


import java.util.List;


import org.springframework.security.access.annotation.Secured;
import org.springframework.transaction.annotation.Transactional;

import cim.entity.Appointment;
import cim.entity.CarOwner;
import cim.entity.InsuranceCompany;
import cim.entity.Policy;
import cim.entity.QuoteRequest;
import cim.entity.Schedule;
import cim.entity.Vehicle;

@Secured({"ROLE_CAROWNER","ROLE_ADMIN","ROLE_MAINTENANCE"})
public interface ICarOwnerService {
	CarOwner getDataByUserId(long userid);
	CarOwner getDataByOwnerId(long ownerid);
	
	@Transactional
	void saveOrUpdate(CarOwner co);
	@Transactional
	void updateCarOwner(CarOwner co);
	
	@Secured({"ROLE_ADMIN"})
	List<CarOwner> getActivationList();
	
	@Secured({"ROLE_CAROWNER"})
	List<Vehicle> getVehicleList(CarOwner co);
	List<Vehicle> getInsuredVehicleList(CarOwner co);
	List<Vehicle> getNotInsuredVehicleList(CarOwner co);
	@Transactional
	public void acceptQuote(QuoteRequest qr) ;
	
	@Transactional
	public void declineQuote(QuoteRequest qr) ;
	
	public void addVehicle(Vehicle vehicle);
	public Vehicle getVehicleById(long vehicleId);
	public QuoteRequest getQuoteRequestById(long id);
	public void askQuote(QuoteRequest qr);
	public List<QuoteRequest> getQuoteRequestByInsuranceCompanyId(InsuranceCompany ic);
	public List<QuoteRequest> getQuoteRequestByVehicleId(Vehicle veh);
	public List<QuoteRequest> getPendingQuoteRequestByCarOwnerId(CarOwner co);
	public Policy getInsurance(Vehicle veh);
	@Transactional
	public void createOrUpdateSchedule(Schedule sch);
	@Transactional
	public void createOrUpdateAppointment(Appointment app);
	
	public Schedule getSchedule(String schId,Long maintenanceCompanyId);
	@Transactional
	public void removeSchedule(Schedule sch);
}
