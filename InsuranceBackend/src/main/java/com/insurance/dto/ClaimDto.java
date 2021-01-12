package com.insurance.dto;

public class ClaimDto {
	String claimReason;
	double claimAmount;
	long claimForPolicyNumber;
	long userId;
	public String getClaimReason() {
		return claimReason;
	}
	public void setClaimReason(String claimReason) {
		this.claimReason = claimReason;
	}
	public double getClaimAmount() {
		return claimAmount;
	}
	public void setClaimAmount(double claimAmount) {
		this.claimAmount = claimAmount;
	}
	public long getClaimForPolicyNumber() {
		return claimForPolicyNumber;
	}
	public void setClaimForPolicyNumber(long claimForPolicyNumber) {
		this.claimForPolicyNumber = claimForPolicyNumber;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	
		
}
