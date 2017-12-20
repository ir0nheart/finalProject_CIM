package cim.model;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;


public class CarOwnerM {
	@NotEmpty
	private String firstName;
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getDriverLicenseNo() {
		return driverLicenseNo;
	}
	public void setDriverLicenseNo(String driverLicenseNo) {
		this.driverLicenseNo = driverLicenseNo;
	}
	public String getDriverLicenseExpiration() {
		return driverLicenseExpiration;
	}
	public void setDriverLicenseExpiration(String driverLicenseExpiration) {
		this.driverLicenseExpiration = driverLicenseExpiration;
	}
	public String getDriverLicenseSince() {
		return driverLicenseSince;
	}
	public void setDriverLicenseSince(String driverLicenseSince) {
		this.driverLicenseSince = driverLicenseSince;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = this.carOwnerStreetNumber + " " + this.carOwnerRoute + " " +
				this.carOwnerCity+", "+this.carOwnerState+ " - " + this.carOwnerCountry + 
				" " + this.carOwnerZipCode;
	}
	private String middleName;
	@NotEmpty
	private String lastName;
	@NotEmpty
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private String dateOfBirth;
	@NotEmpty
	private String driverLicenseNo;
	@NotEmpty
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private String driverLicenseExpiration;
	@NotEmpty
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private String driverLicenseSince;

	private String address;
	
	private String carOwnerStreetNumber;
	private String carOwnerRoute;
	private String carOwnerCity;
	private String carOwnerState;
	private String carOwnerCountry;
	private String carOwnerZipCode;
	public String getCarOwnerStreetNumber() {
		return carOwnerStreetNumber;
	}
	public void setCarOwnerStreetNumber(String carOwnerStreetNumber) {
		this.carOwnerStreetNumber = carOwnerStreetNumber;
	}
	public String getCarOwnerRoute() {
		return carOwnerRoute;
	}
	public void setCarOwnerRoute(String carOwnerRoute) {
		this.carOwnerRoute = carOwnerRoute;
	}
	public String getCarOwnerCity() {
		return carOwnerCity;
	}
	public void setCarOwnerCity(String carOwnerCity) {
		this.carOwnerCity = carOwnerCity;
	}
	public String getCarOwnerState() {
		return carOwnerState;
	}
	public void setCarOwnerState(String carOwnerState) {
		this.carOwnerState = carOwnerState;
	}
	public String getCarOwnerCountry() {
		return carOwnerCountry;
	}
	public void setCarOwnerCountry(String carOwnerCountry) {
		this.carOwnerCountry = carOwnerCountry;
	}
	public String getCarOwnerZipCode() {
		return carOwnerZipCode;
	}
	public void setCarOwnerZipCode(String carOwnerZipCode) {
		this.carOwnerZipCode = carOwnerZipCode;
	}


}
