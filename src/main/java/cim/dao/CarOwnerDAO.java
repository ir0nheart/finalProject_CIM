package cim.dao;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import cim.entity.CarOwner;
import cim.entity.InsuranceCompany;
import cim.entity.QuoteRequest;
import cim.entity.Vehicle;

@Transactional
public class CarOwnerDAO {
	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	// Given the UserID Returns CarOwner Object 	
	public CarOwner getCarOwnerById(long userid) {
		CarOwner carOwner = new CarOwner();
		List<?> list = hibernateTemplate.find("FROM CarOwner WHERE userid=?",
				userid);
		if(!list.isEmpty()) {
			carOwner = (CarOwner)list.get(0);
		}
		return carOwner;
	}
	// Given the CarOwnerID Returns CarOwner Object 	
	public CarOwner getCarOwnerByOwnerId(long ownerid) {
		CarOwner carOwner = new CarOwner();
		List<?> list = hibernateTemplate.find("FROM CarOwner WHERE carownerid=?",
				ownerid);
		if(!list.isEmpty()) {
			carOwner = (CarOwner)list.get(0);
		}
		return carOwner;
	}
	
	// Returns a list of inactive CarOwner Objects	
	public List<CarOwner> getActivationList(){
		List<?> list = hibernateTemplate.find("FROM CarOwner WHERE requestActivate=?", true);
		return (List<CarOwner>) list;
	}
	//Given the CarOwner object Returns list of Vehicles owned by CarOwner
	public List<Vehicle> getVehicleList(CarOwner co){
		List<?> list = hibernateTemplate.find("FROM Vehicle WHERE carownerid=?",co.getCarownerid());
		return (List<Vehicle>) list;
	}
	//Given the CarOwner object Returns list of Vehicles owned by CarOwner and Insured
	public List<Vehicle> getInsuredVehicleList(CarOwner co){
		List<?> list = hibernateTemplate.find("FROM Vehicle WHERE carownerid=? and isInsured=?",co.getCarownerid(),true);
		return (List<Vehicle>) list;
	}
	//Given the CarOwner object Returns list of Vehicles owned by CarOwner and Not Insured
	public List<Vehicle> getNotInsuredVehicleList(CarOwner co){
		List<?> list = hibernateTemplate.find("FROM Vehicle WHERE carownerid=? and isInsured=?",co.getCarownerid(),false);
		return (List<Vehicle>) list;
	}
	
	//Given the Vehicle object Returns list of Quotes asked for vehicle
	public List<QuoteRequest> getQuoteRequestsByVehicleId(Vehicle veh) {
		List<?> list = hibernateTemplate.find("FROM QuoteRequest WHERE vehicleId=?",veh.getVehicleId());
		return (List<QuoteRequest>)list;
	}
	
	//Given the Insurance Company object Returns list of Quotes asked from IC
	public List<QuoteRequest> getQuoteRequestsByInsuranceCompanyId(InsuranceCompany ic) {
		List<?> list = hibernateTemplate.find("FROM QuoteRequest WHERE insuranceCompanyId=?",ic.getInsuranceCompanyId());
		return (List<QuoteRequest>)list;
	}
	
	//Given the CarOwner object Returns list of Pending Quotes asked from IC
	public List<QuoteRequest> getPendingQuoteRequestsByCarOwnerId(CarOwner co) {
		List<?> list = hibernateTemplate.find("FROM QuoteRequest WHERE pendingStatus=? and acceptedStatus=?",true,false);
		List<QuoteRequest> tempList=null;
		if(!list.isEmpty()) {
			tempList=(List<QuoteRequest>)list;
			Iterator<QuoteRequest> iter = tempList.iterator();
			while (iter.hasNext()) {
				QuoteRequest qr = iter.next();
				if(qr.getVehicle().getVehicleOwner().getCarownerid() != co.getCarownerid())
					iter.remove();
			}
		}
		return (List<QuoteRequest>)tempList;
	}
	
	//Given the Quote Id object Returns a Quote object
	public QuoteRequest getQuoteRequestById(long id) {
		QuoteRequest qr = null;
		List<?> list = hibernateTemplate.find("FROM QuoteRequest WHERE quoteId=?",id);
		if(!list.isEmpty()) {
			qr=(QuoteRequest)list.get(0);
		}
		return qr;
	}
}
