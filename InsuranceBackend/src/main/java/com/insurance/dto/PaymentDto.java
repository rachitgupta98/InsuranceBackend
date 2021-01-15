package com.insurance.dto;

import java.time.LocalDate;

public class PaymentDto {
	long userId;
	long policyId;
	double paymentAmount;
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public long getPolicyId() {
		return policyId;
	}
	public void setPolicyId(long policyId) {
		this.policyId = policyId;
	}
	public double getPaymentAmount() {
		return paymentAmount;
	}
	public void setPaymentAmount(double paymentAmount) {
		this.paymentAmount = paymentAmount;
	}
	
}
