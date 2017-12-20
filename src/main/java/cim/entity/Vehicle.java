package cim.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="vehicleTable")
public class Vehicle implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8878737721909005401L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="vehicleId")
	private long vehicleId;
	@Column(name="vehiclePlate")
	private String vehiclePlate;
	@Column(name="vehicleMake")
	private String vehicleMake;
	@Column(name="vehicleModel")
	private String vehicleModel;
	@Column(name="vehicleKM")
	private String vehicleKM;
	@Column(name="vehicleTransmission")
	private String vehicleTransmission;
	@Column(name="vehicleYear")
	private String vehicleYear;
	@Column(name="isInsured",nullable=false,columnDefinition = "tinyint default false")
	private boolean isInsured;
	@OneToOne
	@JoinColumn(name = "carownerid")
	private CarOwner vehicleOwner;
	@OneToOne
	@JoinColumn(name = "insuranceCompanyId")
	private InsuranceCompany vehicleInsuranceCompany;
	public long getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(long vehicleId) {
		this.vehicleId = vehicleId;
	}
	public String getVehiclePlate() {
		return vehiclePlate;
	}
	public void setVehiclePlate(String vehiclePlate) {
		this.vehiclePlate = vehiclePlate;
	}
	public String getVehicleMake() {
		return vehicleMake;
	}
	public void setVehicleMake(String vehicleMake) {
		this.vehicleMake = vehicleMake;
	}
	public String getVehicleModel() {
		return vehicleModel;
	}
	public void setVehicleModel(String vehicleModel) {
		this.vehicleModel = vehicleModel;
	}
	public String getVehicleKM() {
		return vehicleKM;
	}
	public void setVehicleKM(String vehicleKM) {
		this.vehicleKM = vehicleKM;
	}
	public String getVehicleTransmission() {
		return vehicleTransmission;
	}
	public void setVehicleTransmission(String vehicleTransmission) {
		this.vehicleTransmission = vehicleTransmission;
	}
	public String getVehicleYear() {
		return vehicleYear;
	}
	public void setVehicleYear(String vehicleYear) {
		this.vehicleYear = vehicleYear;
	}
	public CarOwner getVehicleOwner() {
		return vehicleOwner;
	}
	public void setVehicleOwner(CarOwner vehicleOwner) {
		this.vehicleOwner = vehicleOwner;
	}
	public InsuranceCompany getVehicleInsuranceCompany() {
		return vehicleInsuranceCompany;
	}
	public void setVehicleInsuranceCompany(InsuranceCompany vehicleInsuranceCompany) {
		this.vehicleInsuranceCompany = vehicleInsuranceCompany;
	}
	public boolean isInsured() {
		return isInsured;
	}
	public void setInsured(boolean isInsured) {
		this.isInsured = isInsured;
	}

	
}
