package cim.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import cim.dao.InsuranceCompanyDAO;
import cim.entity.InsuranceCompany;
import cim.entity.MaintenanceCompany;
import cim.entity.Policy;
import cim.entity.QuoteRequest;
import cim.entity.UserInfo;

public class InsuranceCompanyService implements IInsuranceCompanyService {
	@Autowired
	private InsuranceCompanyDAO insuranceCompanyDAO;
	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	@Override
	public List<InsuranceCompany> getActivationList() {
		
		return insuranceCompanyDAO.getActivationList();
	}

	@Override
	@Transactional
	public void saveOrUpdate(InsuranceCompany co) {
	
		
	}

	@Override
	@Transactional
	public void updateInsuranceCompany(InsuranceCompany co) {
		if(isInsuranceCompanyExists(co)) {
			hibernateTemplate.merge(co);
			}
	}
	public boolean isInsuranceCompanyExists(InsuranceCompany userinfo){
		List<?> list= hibernateTemplate.find("FROM InsuranceCompany WHERE insuranceCompanyId=?", userinfo.getInsuranceCompanyId());
		if(!list.isEmpty()) {
			return true;
		}
		return false;
	}

	@Override
	public InsuranceCompany getDataByUserId(long uid) {
		
		return insuranceCompanyDAO.getInsuranceCompany(uid);
	}

	@Override
	public InsuranceCompany getDataByCompanyId(long insuranceCompanyId) {
		// TODO Auto-generated method stub
		return insuranceCompanyDAO.getInsuranceCompanyById(insuranceCompanyId);
	}

	@Override
	public List<InsuranceCompany> getInsuranceCompanyList() {
		// TODO Auto-generated method stub
		return insuranceCompanyDAO.getInsuranceCompanyList();
	}
	
	public List<QuoteRequest> getQuoteRequestByInsuranceCompanyId(InsuranceCompany ic){
		return insuranceCompanyDAO.getQuoteRequestsByInsuranceCompanyId(ic);
	}
	
	
	public QuoteRequest getQuoteRequestById(long id) {
		return insuranceCompanyDAO.getQuoteRequestById(id);
	}
	@Transactional
	public void updateQuote(QuoteRequest qr) {
		if(isQuoteRequestExists(qr.getQuoteId())) {
			hibernateTemplate.merge(qr);
		}
		
	
	}
	@Override
	public List<QuoteRequest> getPendingQuoteRequestByInsuranceCompanyId(InsuranceCompany ic){
		return insuranceCompanyDAO.getPendingQuoteRequestsByInsuranceCompanyId(ic);
	}
	
	@Override
	public List<QuoteRequest> getAcceptedQuoteRequestByInsuranceCompanyId(InsuranceCompany ic){
		return insuranceCompanyDAO.getAcceptedQuoteRequestsByInsuranceCompanyId(ic);
	}
	public void insureVehicle(QuoteRequest qr) {
		insuranceCompanyDAO.insureVehicle(qr);
	}
	
	public List<Policy> getInsuredVehicles(InsuranceCompany ic){
		List<?> list= hibernateTemplate.find("FROM Policy WHERE insuranceCompanyId=?",ic.getInsuranceCompanyId());
		return (List<Policy>)list;
	}
	public boolean isQuoteRequestExists(long id){
		List<?> list= hibernateTemplate.find("FROM QuoteRequest WHERE quoteId=?",id);
		if(!list.isEmpty()) {
			return true;
		}
		return false;
	}
	@Transactional
	public void updatePolicy(InsuranceCompany ic) {
		Long val = 3L;
		List<?> list= hibernateTemplate.find("FROM Policy WHERE policyId=?",val);
		if(!list.isEmpty()) {
			Policy pol = (Policy)list.get(0);
			pol.setInsuranceCompany(ic);
			hibernateTemplate.delete(pol);
		}
	}

	@Transactional
	public void cancelPolicy(Policy pol) {
		insuranceCompanyDAO.cancelPolicy(pol);
	}
	public Policy getPolicyById(Long policyId) {
		List<?> policyList = hibernateTemplate.find("FROM Policy WHERE policyId=?", policyId);
		if(!policyList.isEmpty()) {
			return (Policy)policyList.get(0);
		}
		return null;
	}
}
