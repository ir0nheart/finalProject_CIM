package cim.entity;

import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="scheduleTable")
public class Schedule implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 2221779804915463996L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="scheduleId")	
	Long scheduleId;
	
	@Column(name="scheduleDate")	
	String scheduleDate;
	
	@OneToOne
	@JoinColumn(name = "maintenanceCompanyId")
	MaintenanceCompany maintenanceCompany;
	
	
	
	public MaintenanceCompany getMaintenanceCompany() {
		return maintenanceCompany;
	}
	public void setMaintenanceCompany(MaintenanceCompany maintenanceCompany) {
		this.maintenanceCompany = maintenanceCompany;
	}

	@Column(name="appointments")
	ArrayList<Long> appointmentIdList;
	public Long getScheduleId() {
		return scheduleId;
	}
	public void setScheduleId(Long scheduleId) {
		this.scheduleId = scheduleId;
	}
	public String getScheduleDate() {
		return scheduleDate;
	}
	public void setScheduleDate(String scheduleDate) {
		this.scheduleDate = scheduleDate;
	}
	public ArrayList<Long> getAppointmentList() {
		return appointmentIdList;
	}
	public void setAppointmentList(ArrayList<Long> appointmentIdList) {
		this.appointmentIdList = appointmentIdList;
	}

	public void addToAppointmentList(Long appointmentId) {
		this.appointmentIdList.add(appointmentId);
	}
	public Schedule() {
		this.appointmentIdList = new ArrayList<Long>();
	}
}
