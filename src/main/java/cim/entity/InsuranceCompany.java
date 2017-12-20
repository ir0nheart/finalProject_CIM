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
@Table(name="insuranceCompanyTable")
public class InsuranceCompany implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3861513771187560923L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="insuranceCompanyId")
	private long insuranceCompanyId;
	
	@OneToOne
	@JoinColumn(name = "userid")
	private UserInfo userinfo;
	
	@Column(name="requestActivate",nullable=false,columnDefinition = "tinyint default false")
	private boolean requestActivate;
	
	@Column(name="insuranceCompanyName")
	private String insuranceCompanyName;
	
	@Column(name="insuranceCompanyContact")
	private String insuranceCompanyContact;
	
	@Transient
	private MultipartFile photo;
	
	@Lob private byte[] photoBytes;

	private String photoContentType;
	private String photoFileName;
	
	

	public long getInsuranceCompanyId() {
		return insuranceCompanyId;
	}

	public void setInsuranceCompanyId(long insuranceCompanyId) {
		this.insuranceCompanyId = insuranceCompanyId;
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
	
	public InsuranceCompany() {
		
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
	
	
}
