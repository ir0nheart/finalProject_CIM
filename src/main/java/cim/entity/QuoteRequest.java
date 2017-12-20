package cim.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="quoteTable")
public class QuoteRequest {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="quoteId")
	private long quoteId;
	@OneToOne
	@JoinColumn(name = "vehicleId")
	private Vehicle vehicle;
	@OneToOne
	@JoinColumn(name="insuranceCompanyId")
	private InsuranceCompany insuranceCompany;
	
	@Column(name="quoteTerm")
	private int quoteTerm;
	@Column(name="pendingStatus",nullable=false,columnDefinition = "tinyint default false")
	private boolean pendingStatus;
	@Column(name="acceptedStatus",nullable=false,columnDefinition = "tinyint default false")
	private boolean acceptedStatus;
	@OneToOne
	@JoinColumn(name="policyId")
	private Policy quotedPolicy;
	@Column(name="quoteCost")
	private Double quoteCost;
	@Column(name="isInsured",nullable=false,columnDefinition = "tinyint default false")
	private boolean isInsured;
	
	public long getQuoteId() {
		return quoteId;
	}
	public void setQuoteId(long quoteId) {
		this.quoteId = quoteId;
	}
	public Vehicle getVehicle() {
		return vehicle;
	}
	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}
	public InsuranceCompany getInsuranceCompany() {
		return insuranceCompany;
	}
	public void setInsuranceCompany(InsuranceCompany insuranceCompany) {
		this.insuranceCompany = insuranceCompany;
	}
	public int getQuoteTerm() {
		return quoteTerm;
	}
	public void setQuoteTerm(int quoteTerm) {
		this.quoteTerm = quoteTerm;
	}
	public boolean isPendingStatus() {
		return pendingStatus;
	}
	public void setPendingStatus(boolean pendingStatus) {
		this.pendingStatus = pendingStatus;
	}
	public boolean isAcceptedStatus() {
		return acceptedStatus;
	}
	public void setAcceptedStatus(boolean acceptedStatus) {
		this.acceptedStatus = acceptedStatus;
	}
	public Policy getQuotedPolicy() {
		return quotedPolicy;
	}
	public void setQuotedPolicy(Policy quotedPolicy) {
		this.quotedPolicy = quotedPolicy;
	}
	public double getQuoteCost() {
		return quoteCost;
	}
	public void setQuoteCost(double quoteCost) {
		this.quoteCost = quoteCost;
	}
	public boolean isInsured() {
		return isInsured;
	}
	public void setInsured(boolean isInsured) {
		this.isInsured = isInsured;
	}
	public void setQuoteCost(Double quoteCost) {
		this.quoteCost = quoteCost;
	}
	
	
	

}
