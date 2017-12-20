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
@Table(name="maintenanceCompanyTable")
public class MaintenanceCompany implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5052600308853227844L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="maintenanceCompanyId")
	private long maintenanceCompanyId;
	
	@OneToOne
	@JoinColumn(name = "userid")
	private UserInfo userinfo;
	
	@Column(name="requestActivate",nullable=false,columnDefinition = "tinyint default false")
	private boolean requestActivate;
	
	@Column(name="maintenanceCompanyName")
	private String maintenanceCompanyName;
	@Column(name="maintenanceCompanyContact")
	private String maintenanceCompanyContact;
	@Column(name="maintenanceCompanyCity")
	private String maintenanceCompanyCity;
	@Column(name="maintenanceCompanyState")
	private String maintenanceCompanyState;
	
	@Column(name="maintenanceCompanyStreetNumber")
	private String maintenanceCompanyStreetNumber;
	@Column(name="maintenanceCompanyRoute")
	private String maintenanceCompanyRoute;
	@Column(name="maintenanceCompanyZipCode")
	private String maintenanceCompanyZipCode;
	@Column(name="maintenanceCompanyCountry")
	private String maintenanceCompanyCountry;
	
	private String maintenanceCompanyAddress;
	
	private String calendarId;
	
	
	public String getCalendarId() {
		return calendarId;
	}

	public void setCalendarId(String calendarId) {
		this.calendarId = calendarId;
	}

	@Transient
	private MultipartFile photo;
	
	@Lob private byte[] photoBytes;

	private String photoContentType;
	private String photoFileName;
	
	
	public MaintenanceCompany() {
		
	}

	public long getMaintenanceCompanyId() {
		return maintenanceCompanyId;
	}

	public void setMaintenanceCompanyId(long maintenanceCompanyId) {
		this.maintenanceCompanyId = maintenanceCompanyId;
	}

	public UserInfo getUserinfo() {
		return userinfo;
	}

	public void setUserinfo(UserInfo userinfo) {
		this.userinfo = userinfo;
	}

	public boolean isRequestActivate() {
		return requestActivate;
	}

	public void setRequestActivate(boolean requestActivate) {
		this.requestActivate = requestActivate;
	}

	public String getMaintenanceCompanyName() {
		return maintenanceCompanyName;
	}

	public void setMaintenanceCompanyName(String maintenanceCompanyName) {
		this.maintenanceCompanyName = maintenanceCompanyName;
	}

	public String getMaintenanceCompanyContact() {
		return maintenanceCompanyContact;
	}

	public void setMaintenanceCompanyContact(String maintenanceCompanyContact) {
		this.maintenanceCompanyContact = maintenanceCompanyContact;
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

	@Override
	public String toString() {
		
		return "Maintenance Company ID: " + this.maintenanceCompanyId + "\n" +
				"Maintenance Company Name: " + this.maintenanceCompanyName + "\n" +
				"Maintenance Company Contact: " + this.maintenanceCompanyContact + "\n"+
				"Maintenance Company State: " + this.maintenanceCompanyState + "\n" +
				"Maintenance Company City: " + this.maintenanceCompanyCity + "\n";
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
	
	public String getMaintenanceCompanyAddress() {
		return this.maintenanceCompanyAddress;
	}

	public void setMaintenanceCompanyAddress() {
		this.maintenanceCompanyAddress = this.maintenanceCompanyStreetNumber + " " + this.maintenanceCompanyRoute + " " +
				this.maintenanceCompanyCity+", "+this.maintenanceCompanyState+ " - " + this.maintenanceCompanyCountry + 
				" " + this.maintenanceCompanyZipCode;
	}
	
}
