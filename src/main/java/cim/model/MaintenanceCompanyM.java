package cim.model;

import org.hibernate.validator.constraints.NotEmpty;

public class MaintenanceCompanyM {
	public String getMaintenanceCompanyName() {
		return maintenanceCompanyName;
	}
	public void setMaintenanceCompanyName(String maintenanceCompanyName) {
		this.maintenanceCompanyName = maintenanceCompanyName;
	}
	public String getMaintenanceCompanyContact() {
		return maintenanceCompanyContact;
	}
	public void setMaintenanceCompanyContact(String maintenanceCompanContact) {
		this.maintenanceCompanyContact = maintenanceCompanContact;
	}
	public String getMaintenanceCompanyCity() {
		return maintenanceCompanyCity;
	}
	public void setMaintenanceCompanyCity(String maintenanceCompanyCity) {
		this.maintenanceCompanyCity = maintenanceCompanyCity;
	}
	public String getMaintenanceCompanyState() {
		return maintenanceCompanyState;
	}
	public void setMaintenanceCompanyState(String maintenanceCompanyState) {
		this.maintenanceCompanyState = maintenanceCompanyState;
	}
	@NotEmpty
	private String maintenanceCompanyName;
	@NotEmpty
	private String maintenanceCompanyContact;
	
	private String maintenanceCompanyCity;
	@NotEmpty
	private String maintenanceCompanyState;

	private String maintenanceCompanyStreetNumber;

	private String maintenanceCompanyRoute;

	private String maintenanceCompanyZipCode;
	@NotEmpty
	private String maintenanceCompanyCountry;
	public MaintenanceCompanyM() {
		
	}
	public String getMaintenanceCompanyStreetNumber() {
		return maintenanceCompanyStreetNumber;
	}
	public void setMaintenanceCompanyStreetNumber(String maintenanceCompanyStreetNumber) {
		this.maintenanceCompanyStreetNumber = maintenanceCompanyStreetNumber;
	}
	public String getMaintenanceCompanyRoute() {
		return maintenanceCompanyRoute;
	}
	public void setMaintenanceCompanyRoute(String maintenanceCompanyRoute) {
		this.maintenanceCompanyRoute = maintenanceCompanyRoute;
	}
	public String getMaintenanceCompanyZipCode() {
		return maintenanceCompanyZipCode;
	}
	public void setMaintenanceCompanyZipCode(String maintenanceCompanyZipCode) {
		this.maintenanceCompanyZipCode = maintenanceCompanyZipCode;
	}
	
	public String getMaintenanceCompanyCountry() {
		return maintenanceCompanyCountry;
	}

	public void setMaintenanceCompanyCountry(String maintenanceCompanyCountry) {
		this.maintenanceCompanyCountry = maintenanceCompanyCountry;
	}
}
