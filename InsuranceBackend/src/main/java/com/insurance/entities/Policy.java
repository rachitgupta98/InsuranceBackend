package com.insurance.entities;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.ManyToAny;
import org.hibernate.annotations.NaturalId;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tbl_policy_data")
public class Policy {

	@Id
	@SequenceGenerator(name = "seq_insr_policynumber", initialValue = 1000, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_insr_policynumber")
	long policyId;
	
//	@Column(unique = true,nullable = false)
//	long policyNumber;
	
	String planType;
	//@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
	LocalDate purchaseDate;
	double premiumAmount;
	//@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
	LocalDate policyStartDate;
	//@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
	LocalDate policyEndDate;
	boolean isExpired;
	long insuranceAmount;
	int planYear;
	
	@ManyToOne
	@JoinColumn(name = "userId")
	User user;

	@OneToOne
	@JoinColumn(name = "vehicleId")
	Vehicle vehicle;

	@OneToMany(mappedBy = "policy",cascade = CascadeType.ALL)
	List<Claim> claims;

	@OneToOne(mappedBy = "policy", cascade = CascadeType.ALL)
	//@JsonIgnore
	Payment payment;

	public long getPolicyId() {
		return policyId;
	}

	public void setPolicyId(long policyId) {
		this.policyId = policyId;
	}
	

	public int getPlanYear() {
		return planYear;
	}

	public void setPlanYear(int planYear) {
		this.planYear = planYear;
	}

//	public long getPolicyNumber() {
//		return policyNumber;
//	}
//
//	public void setPolicyNumber(long policyNumber) {
//		this.policyNumber = policyNumber;
//	}

	public String getPlanType() {
		return planType;
	}

	public void setPlanType(String planType) {
		this.planType = planType;
	}

	public double getPremiumAmount() {
		return premiumAmount;
	}

	public void setPremiumAmount(double premiumAmount) {
		this.premiumAmount = premiumAmount;
	}

	
	public LocalDate getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(LocalDate purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public LocalDate getPolicyStartDate() {
		return policyStartDate;
	}

	public void setPolicyStartDate(LocalDate policyStartDate) {
		this.policyStartDate = policyStartDate;
	}

	public LocalDate getPolicyEndDate() {
		return policyEndDate;
	}

	public void setPolicyEndDate(LocalDate policyEndDate) {
		this.policyEndDate = policyEndDate;
	}

	public boolean isExpired() {
		return isExpired;
	}

	public void setExpired(boolean isExpired) {
		this.isExpired = isExpired;
	}

	public long getInsuranceAmount() {
		return insuranceAmount;
	}

	public void setInsuranceAmount(long insuranceAmount) {
		this.insuranceAmount = insuranceAmount;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}



	public List<Claim> getClaims() {
		return claims;
	}

	public void setClaims(List<Claim> claims) {
		this.claims = claims;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

}
