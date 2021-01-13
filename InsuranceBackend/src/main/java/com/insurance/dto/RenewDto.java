package com.insurance.dto;

public class RenewDto {

	long policyId;
	String registrationNumber;
	int planYear;
	
	public int getPlanYear() {
		return planYear;
	}
	public void setPlanYear(int planYear) {
		this.planYear = planYear;
	}
	public long getPolicyId() {
		return policyId;
	}
	public void setPolicyId(long policyId) {
		this.policyId = policyId;
	}
	public String getRegistrationNumber() {
		return registrationNumber;
	}
	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}
	
}
