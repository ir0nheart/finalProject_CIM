package cim.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="appointmentTable")
public class Appointment implements Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1156808481160523559L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="appointmentId")	
	private Long appointmentId; 	
	
	@Column(name="appointmentToId")
	private Long appointmentToId; // maintenance
	
	@Column(name="appointmentFromId")
	private Long appointmentFromId; // carowner
	
	@Column(name="appointmentForId")
	private Long appointmentForId; // vehicle
	
	@Column(name="appointmentTime")
	private Integer appointmentTime;
	

	@Column(name = "scheduleDate")
	private String scheduleDate;
	
	@Column(name="jsonAppointment",length=5000)
	private String jsonAppointment;
	
	
	@Column(name="jsonAppointmentEvent",length=5000)
	private String jsonAppointmentEvent;


	
	public String getJsonAppointmentEvent() {
		return jsonAppointmentEvent;
	}
	public void setJsonAppointmentEvent(String jsonAppointmentEvent) {
		this.jsonAppointmentEvent = jsonAppointmentEvent;
	}
	public String getJsonAppointment() {
		return jsonAppointment;
	}
	public void setJsonAppointment(String jsonAppointment) {
		this.jsonAppointment = jsonAppointment;
	}
	public String getScheduleDate() {
		return scheduleDate;
	}
	public void setScheduleDate(String scheduleDate) {
		this.scheduleDate = scheduleDate;
	}
	public Integer getAppointmentTime() {
		return appointmentTime;
	}
	public void setAppointmentTime(Integer appointmentTime) {
		this.appointmentTime = appointmentTime;
	}
	public Long getAppointmentId() {
		return appointmentId;
	}
	public void setAppointmentId(Long appointmentId) {
		this.appointmentId = appointmentId;
	}
	public Long getAppointmentToId() {
		return appointmentToId;
	}
	public void setAppointmentToId(Long appointmentToId) {
		this.appointmentToId = appointmentToId;
	}
	public Long getAppointmentFromId() {
		return appointmentFromId;
	}
	public void setAppointmentFromId(Long appointmentFromId) {
		this.appointmentFromId = appointmentFromId;
	}
	public Long getAppointmentForId() {
		return appointmentForId;
	}
	public void setAppointmentForId(Long appointmentForId) {
		this.appointmentForId = appointmentForId;
	}


}

