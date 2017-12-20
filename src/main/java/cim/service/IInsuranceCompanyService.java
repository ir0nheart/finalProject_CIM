package cim.service;

import java.util.List;

import org.springframework.security.access.annotation.Secured;
import org.springframework.transaction.annotation.Transactional;

import cim.entity.InsuranceCompany;
import cim.entity.Policy;
import cim.entity.QuoteRequest;



@Secured({"ROLE_INSURANCE","ROLE_ADMIN","ROLE_CAROWNER"})
public interface IInsuranceCompanyService {

	public InsuranceCompany getDataByUserId(long uid) ;
	public InsuranceCompany getDataByCompanyId(long insuranceCompanyId) ;
	@Transactional
	void saveOrUpdate(InsuranceCompany co);
	@Transactional
	void updateInsuranceCompany(InsuranceCompany co);
	
	@Secured({"ROLE_ADMIN"})
	List<InsuranceCompany> getActivationList();
	
	List<InsuranceCompany> getInsuranceCompanyList();
	public List<QuoteRequest> getQuoteRequestByInsuranceCompanyId(InsuranceCompany ic);
	public List<QuoteRequest> getPendingQuoteRequestByInsuranceCompanyId(InsuranceCompany ic);
	public List<QuoteRequest> getAcceptedQuoteRequestByInsuranceCompanyId(InsuranceCompany ic);
	public QuoteRequest getQuoteRequestById(long id);
	public void updateQuote(QuoteRequest qr);
	public void insureVehicle(QuoteRequest qr);
	public List<Policy> getInsuredVehicles(InsuranceCompany ic);
	@Transactional
	public void updatePolicy(InsuranceCompany ic);
	@Transactional
	public void cancelPolicy(Policy pol);
	
	public Policy getPolicyById(Long policyId);
	
}
