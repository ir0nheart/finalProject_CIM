package cim.model;

import org.hibernate.validator.constraints.NotEmpty;

public class VehicleM {
	@NotEmpty
	private String vehicleMake;
	@NotEmpty
	private String vehicleModel;
	@NotEmpty
	private String vehicleYear;
	@NotEmpty
	private String vehicleKM;
	@NotEmpty
	private String vehicleTransmission;
	@NotEmpty
	private String vehiclePlate;
	
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
	public String getVehicleYear() {
		return vehicleYear;
	}
	public void setVehicleYear(String vehicleYear) {
		this.vehicleYear = vehicleYear;
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
	public String getVehiclePlate() {
		return vehiclePlate;
	}
	public void setVehiclePlate(String vehiclePlate) {
		this.vehiclePlate = vehiclePlate;
	}
}
