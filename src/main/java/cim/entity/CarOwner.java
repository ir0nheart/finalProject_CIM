package cim.entity;

import java.io.IOException;
import java.io.Serializable;


import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;
@Entity
@Table(name="carOwnerTable")
public class CarOwner implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5097071342160987516L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="carownerid")
	private long carownerid;
	@OneToOne
	@JoinColumn(name = "userid")
	private UserInfo userinfo;
	
	@Column(name="requestActivate",nullable=false,columnDefinition = "tinyint default false")
	private boolean requestActivate;

	public boolean isRequestActivate() {
		return requestActivate;
	}
	public void setRequestActivate(boolean requestActivate) {
		this.requestActivate = requestActivate;
	}
	
	@Transient
	private MultipartFile photo;
	
	@Lob private byte[] photoBytes;

	private String photoContentType;
	private String photoFileName;
	@Column(name="firstName")
private String firstName;
	@Column(name="middleName")
private String middleName;
	@Column(name="lastName")
private String lastName;
	@Column(name="dateOfBirth")
private String dateOfBirth;
	@Column(name="driverLicenseNo")
private String driverLicenseNo;
	@Column(name="driverLicenseSince")
private String driverLicenseSince;
	@Column(name="driverLicenseExpiration")
private String driverLicenseExpiration;
	@Column(name="address")
private String address;
//	@Column(name="vehicles")
//private List<Vehicle> vehicles;
	


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
public String getDriverLicenseSince() {
	return driverLicenseSince;
}
public void setDriverLicenseSince(String driverLicenseSince) {
	this.driverLicenseSince = driverLicenseSince;
}
public String getDriverLicenseExpiration() {
	return driverLicenseExpiration;
}
public void setDriverLicenseExpiration(String driverLicenseExpiration) {
	this.driverLicenseExpiration = driverLicenseExpiration;
}
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address  = this.carOwnerStreetNumber + " " + this.carOwnerRoute + " " +
			this.carOwnerCity+", "+this.carOwnerState+ " - " + this.carOwnerCountry + 
			" " + this.carOwnerZipCode;
}
//public List<Vehicle> getVehicles() {
//	return vehicles;
//}
//public void setVehicles(List<Vehicle> vehicles) {
//	this.vehicles = vehicles;
//}
public long getCarownerid() {
	return carownerid;
}
public void setCarownerid(long carownerid) {
	this.carownerid = carownerid;
}
public UserInfo getUserinfo() {
	return userinfo;
}
public void setUserinfo(UserInfo userinfo) {
	this.userinfo = userinfo;
}
public String getFirstName() {
	return firstName;
}

public CarOwner() {
	
}
public MultipartFile getPhoto() {
	return photo;
}
public void setPhoto(MultipartFile photo) {
	this.photo = photo;
	setPhotoContentType(photo.getContentType());
	setPhotoFileName(photo.getOriginalFilename());
	try {
		setPhotoBytes(photo.getBytes());
	} catch (IOException e) {
		e.printStackTrace();
	}
}
public byte[] getPhotoBytes() {
	return photoBytes;
}
public void setPhotoBytes(byte[] photoBytes) {
	this.photoBytes = photoBytes;
}
public String getPhotoContentType() {
	return photoContentType;
}
public void setPhotoContentType(String photoContentType) {
	this.photoContentType = photoContentType;
}
public String getPhotoFileName() {
	return photoFileName;
}
public void setPhotoFileName(String photoFileName) {
	this.photoFileName = photoFileName;
}



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

private String carOwnerStreetNumber;
private String carOwnerRoute;
private String carOwnerCity;
private String carOwnerState;
private String carOwnerCountry;
private String carOwnerZipCode;

}
