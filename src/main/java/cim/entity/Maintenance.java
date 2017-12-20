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
@Table(name="vehicleMaintenance")
public class Maintenance implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7091221117294519899L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="maintenanceId")
	private Long maintenanceId;
	
	
	@OneToOne
	@JoinColumn(name = "carownerid")
	private CarOwner carOwner;
	
	@OneToOne
	@JoinColumn(name = "maintenanceCompanyId")
	private MaintenanceCompany maintenanceCompany;
	@OneToOne
	@JoinColumn(name="vehicleId")
	private Vehicle vehicle;
	@OneToOne
	@JoinColumn(name="appointmentId")
	private Appointment appointment;

	public Long getMaintenanceId() {
		return maintenanceId;
	}

	public void setMaintenanceId(Long maintenanceId) {
		this.maintenanceId = maintenanceId;
	}

	public CarOwner getCarOwner() {
		return carOwner;
	}

	public void setCarOwner(CarOwner carOwner) {
		this.carOwner = carOwner;
	}

	public MaintenanceCompany getMaintenanceCompany() {
		return maintenanceCompany;
	}

	public void setMaintenanceCompany(MaintenanceCompany maintenanceCompany) {
		this.maintenanceCompany = maintenanceCompany;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public Appointment getAppointment() {
		return appointment;
	}

	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
	}
	
	private String maintenanceStatus;

	public String getMaintenanceStatus() {
		return maintenanceStatus;
	}

	public void setMaintenanceStatus(String maintenanceStatus) {
		this.maintenanceStatus = maintenanceStatus;
	}
	
	
}
