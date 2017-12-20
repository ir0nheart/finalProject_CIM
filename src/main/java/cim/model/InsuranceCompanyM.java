package cim.model;

import org.hibernate.validator.constraints.NotEmpty;

public class InsuranceCompanyM {
	@NotEmpty
	private String insuranceCompanyName;
	public String getInsuranceCompanyName() {
		return insuranceCompanyName;
	}
	public void setInsuranceCompanyName(String insuranceCompanyName) {
		this.insuranceCompanyName = insuranceCompanyName;
	}
	public String getInsuranceCompanyContact() {
		return insuranceCompanyContact;
	}
	public void setInsuranceCompanyContact(String insuranceCompanyContact) {
		this.insuranceCompanyContact = insuranceCompanyContact;
	}
	@NotEmpty
	private String insuranceCompanyContact;
	public InsuranceCompanyM() {
		
	}

}
