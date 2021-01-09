package com.insurance.service;

import java.util.List;

import com.insurance.apiResponse.ApiResponse;
import com.insurance.entities.Payment;

public interface PaymentService {

	public ApiResponse pay(Payment payment);
	public List<Payment> viewAllPayments();
	public ApiResponse findPaymentByPaymentId(long paymentId);
	public ApiResponse findPaymentByPolicyId(long policyId);
}
