package cim.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;
import cim.entity.InsuranceCompany;
import cim.entity.Policy;
import cim.entity.QuoteRequest;
import cim.entity.UserInfo;
import cim.entity.Vehicle;

@Transactional
public class InsuranceCompanyDAO {
	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	public List<InsuranceCompany> getActivationList(){
		List<?> list = hibernateTemplate.find("FROM InsuranceCompany WHERE requestActivate=?", true);
		return (List<InsuranceCompany>) list;
	}
	
	public InsuranceCompany getInsuranceCompany(long userid) {
		InsuranceCompany insuranceCompany = new InsuranceCompany();
		List<?> list = hibernateTemplate.find("FROM InsuranceCompany WHERE userid=?",
				userid);
		if(!list.isEmpty()) {
			insuranceCompany = (InsuranceCompany)list.get(0);
		}
		return insuranceCompany;
	}
	public InsuranceCompany getInsuranceCompanyById(long insuranceCompanyId) {
		InsuranceCompany insuranceCompany = new InsuranceCompany();
		List<?> list = hibernateTemplate.find("FROM InsuranceCompany WHERE insuranceCompanyId=?",
				insuranceCompanyId);
		if(!list.isEmpty()) {
			insuranceCompany = (InsuranceCompany)list.get(0);
		}
		return insuranceCompany;
	}
	
	public List<InsuranceCompany> getInsuranceCompanyList(){
		List<?> list = hibernateTemplate.find("FROM InsuranceCompany WHERE requestActivate=?", true);
		List<InsuranceCompany> tempList= (List<InsuranceCompany>) list;
		Iterator<InsuranceCompany> iter = tempList.iterator();
		
		while(iter.hasNext()) {
			InsuranceCompany ic = iter.next();
			UserInfo UserInfo = new UserInfo();
			List<?> listUser = hibernateTemplate.find("FROM UserInfo WHERE username=?",
					ic.getUserinfo().getUsername());
			if(!listUser.isEmpty()) {
				UserInfo = (UserInfo)listUser.get(0);
			}
			if(!(UserInfo.isActive())) {
		        iter.remove();
		        }
		}
		return tempList;
	}
	
	public List<QuoteRequest> getQuoteRequestsByInsuranceCompanyId(InsuranceCompany ic) {
		List<?> list = hibernateTemplate.find("FROM QuoteRequest WHERE insuranceCompanyId=? and pendingStatus=?",ic.getInsuranceCompanyId(),false);
		return (List<QuoteRequest>)list;
	}
	
	public List<QuoteRequest> getPendingQuoteRequestsByInsuranceCompanyId(InsuranceCompany ic) {
		List<?> list = hibernateTemplate.find("FROM QuoteRequest WHERE insuranceCompanyId=? and pendingStatus=? and acceptedStatus=?",ic.getInsuranceCompanyId(),true,false);
		return (List<QuoteRequest>)list;
	}
	
	public List<QuoteRequest> getAcceptedQuoteRequestsByInsuranceCompanyId(InsuranceCompany ic) {
		List<?> list = hibernateTemplate.find("FROM QuoteRequest WHERE insuranceCompanyId=? and acceptedStatus=? and isInsured=?",ic.getInsuranceCompanyId(),true,false);
		return (List<QuoteRequest>)list;
	}
	
	public QuoteRequest getQuoteRequestById(long id) {
		QuoteRequest qr = null;
		List<?> list = hibernateTemplate.find("FROM QuoteRequest WHERE quoteId=?",id);
		if(!list.isEmpty()) {
			qr=(QuoteRequest)list.get(0);
		}
		return qr;
	}
	@Transactional
	public void cancelPolicy(Policy pol) {
		QuoteRequest qr = pol.getQuoteRequest();
		Vehicle veh = qr.getVehicle();
		veh.setInsured(false);
		veh.setVehicleInsuranceCompany(null);
		hibernateTemplate.update(veh);
		hibernateTemplate.delete(qr);
		hibernateTemplate.delete(pol);
	}
	@Transactional
	public void insureVehicle(QuoteRequest qr) {
		
		hibernateTemplate.merge(qr);
		Vehicle veh = qr.getVehicle();
		veh.setInsured(true);
		veh.setVehicleInsuranceCompany(qr.getInsuranceCompany());
		hibernateTemplate.merge(veh);
		Policy policy = new Policy();
		policy.setQuoteRequest(qr);
		String pattern = "yyyy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String dateYear = simpleDateFormat.format(new Date());
		 pattern = "MM";
		 simpleDateFormat = new SimpleDateFormat(pattern);
		String dateMonth = simpleDateFormat.format(new Date());
		 pattern = "dd";
		 simpleDateFormat = new SimpleDateFormat(pattern);
		String dateDay = simpleDateFormat.format(new Date());
		int mo = Integer.valueOf(dateMonth);
		int yr= Integer.valueOf(dateYear);
		if(mo+qr.getQuoteTerm()>12) yr++;
		mo=(mo + qr.getQuoteTerm())%12;
		String expirationDate=String.valueOf(yr)+"-"+String.valueOf(mo)+"-"+dateDay;
		policy.setExpirationDate(expirationDate);
		policy.setInsuranceCompany(qr.getInsuranceCompany());
		hibernateTemplate.saveOrUpdate(policy);
		qr.setQuotedPolicy(policy);
		hibernateTemplate.merge(qr);
		
	}
}
