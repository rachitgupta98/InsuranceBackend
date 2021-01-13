
package com.insurance.dto;

public class ClaimDto {

	String claimReason;
	double claimAmount;
	long claimForPolicyId;
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
	
	public long getClaimForPolicyId() {
		return claimForPolicyId;
	}
	public void setClaimForPolicyId(long claimForPolicyId) {
		this.claimForPolicyId = claimForPolicyId;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	
	
}
