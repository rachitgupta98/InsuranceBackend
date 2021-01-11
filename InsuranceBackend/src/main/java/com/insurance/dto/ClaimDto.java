package com.insurance.dto;

public class ClaimDto {
		long PolicyNumber;
		long UserId;
		double claimAmount;
		String claimReason;
		public long getPolicyNumber() {
			return PolicyNumber;
		}
		public void setPolicyNumber(long policyNumber) {
			PolicyNumber = policyNumber;
		}
		public long getUserId() {
			return UserId;
		}
		public void setUserId(long userId) {
			UserId = userId;
		}
		public double getClaimAmount() {
			return claimAmount;
		}
		public void setClaimAmount(double claimAmount) {
			this.claimAmount = claimAmount;
		}
		public String getClaimReason() {
			return claimReason;
		}
		public void setClaimReason(String claimReason) {
			this.claimReason = claimReason;
		}
		
}
