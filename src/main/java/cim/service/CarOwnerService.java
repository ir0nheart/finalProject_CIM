package cim.service;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;

import cim.dao.CarOwnerDAO;
import cim.entity.Appointment;
import cim.entity.CarOwner;
import cim.entity.InsuranceCompany;
import cim.entity.Policy;
import cim.entity.QuoteRequest;
import cim.entity.Schedule;
import cim.entity.Vehicle;

public class CarOwnerService implements ICarOwnerService{
	@Autowired
	private CarOwnerDAO carOwnerDAO;
	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	@Override
	public CarOwner getDataByUserId(long userid) {
		return carOwnerDAO.getCarOwnerById(userid);
	}
	@Override
	public CarOwner getDataByOwnerId(long ownerid) {
		return carOwnerDAO.getCarOwnerByOwnerId(ownerid);
	}


	@Override
	public void saveOrUpdate(CarOwner co) {
		hibernateTemplate.saveOrUpdate(co);	
		
	}

	@Override
	public void updateCarOwner(CarOwner co) {
		if(isCarOwnerExists(co)) {
			hibernateTemplate.merge(co);
		}
		
		
	}
	

	public boolean isCarOwnerExists(CarOwner co){
		List<?> list= hibernateTemplate.find("FROM CarOwner WHERE carownerid=?",co.getCarownerid());
		if(!list.isEmpty()) {
			return true;
		}
		return false;
	}

	@Override
	public List<CarOwner> getActivationList() {
		
		return carOwnerDAO.getActivationList();
	}
	@Override
	public void addVehicle(Vehicle vehicle) {
		hibernateTemplate.saveOrUpdate(vehicle);
		
	}
	@Override
	public List<Vehicle> getVehicleList(CarOwner co) {

		return carOwnerDAO.getVehicleList(co);
	}
	
	@Override
	public List<Vehicle> getInsuredVehicleList(CarOwner co) {

		return carOwnerDAO.getInsuredVehicleList(co);
	}
	
	@Override
	public List<Vehicle> getNotInsuredVehicleList(CarOwner co) {

		return carOwnerDAO.getNotInsuredVehicleList(co);
	}
	@Override
	public void askQuote(QuoteRequest qr) {
		hibernateTemplate.saveOrUpdate(qr);		
	}
	
	@Override
	public List<QuoteRequest> getQuoteRequestByVehicleId(Vehicle veh) {

		return carOwnerDAO.getQuoteRequestsByVehicleId(veh);
	}
	@Override
	public List<QuoteRequest> getQuoteRequestByInsuranceCompanyId(InsuranceCompany ic) {

		return carOwnerDAO.getQuoteRequestsByInsuranceCompanyId(ic);
	}
	@Override
	public Vehicle getVehicleById(long vehicleId) {
		Vehicle veh=null;
		
		List<?> list= hibernateTemplate.find("FROM Vehicle WHERE vehicleId=?",vehicleId);
		if(!list.isEmpty()) {
			veh=(Vehicle)list.get(0);
		}
		
		return veh;
	}
	
	@Override
	public List<QuoteRequest> getPendingQuoteRequestByCarOwnerId(CarOwner co){
		return carOwnerDAO.getPendingQuoteRequestsByCarOwnerId(co);
	}
	@Override
	public QuoteRequest getQuoteRequestById(long id) {
		return carOwnerDAO.getQuoteRequestById(id);
	}
	
	public boolean isQuoteRequestExists(long id){
		List<?> list= hibernateTemplate.find("FROM QuoteRequest WHERE quoteId=?",id);
		if(!list.isEmpty()) {
			return true;
		}
		return false;
	}
	@Override
	public void acceptQuote(QuoteRequest qr) {
		hibernateTemplate.merge(qr);
		
	}
	@Override
	public void declineQuote(QuoteRequest qr) {
		hibernateTemplate.delete(qr);
		
	}
	@Override
	public Policy getInsurance(Vehicle veh) {
		List<?> list = hibernateTemplate.find("FROM Policy");
		if(!list.isEmpty()) {
			Iterator<Policy> iter = (Iterator<Policy>)list.iterator();
			while(iter.hasNext()) {
				Policy pol = iter.next();
				QuoteRequest qr = pol.getQuoteRequest();
				Vehicle veh2 = qr.getVehicle();
				if(veh.getVehicleId()==veh2.getVehicleId())
					return pol;
				
			}
		}
		return null;
	}
	
	@Override
	public void createOrUpdateSchedule(Schedule sch) {
		List<?> list = hibernateTemplate.find("FROM Schedule WHERE scheduleId=?",sch.getScheduleId());
		if(list.isEmpty()) {
			hibernateTemplate.saveOrUpdate(sch);
		}else {
			hibernateTemplate.merge(sch);
		}
		
	}
	
	@Override
	public void createOrUpdateAppointment(Appointment app) {
		List<?> list = hibernateTemplate.find("FROM Appointment WHERE appointmentId=?", app.getAppointmentId());
		if(list.isEmpty()) {
			hibernateTemplate.saveOrUpdate(app);
		}else {
			hibernateTemplate.merge(app);
		}
		
		
	}
	@Override
	public Schedule getSchedule(String schId,Long maintenanceCompanyId) {
		Schedule sch=null;
		List<?> list = hibernateTemplate.find("FROM Schedule WHERE scheduleDate=? and maintenanceCompanyId=?", schId,maintenanceCompanyId);
		if(!list.isEmpty()) {
			sch = (Schedule)list.get(0);
		}
		return sch;
	}
	@Override
	public void removeSchedule(Schedule sch) {
		hibernateTemplate.merge(sch);
		
	}

	
	
	
	


}
