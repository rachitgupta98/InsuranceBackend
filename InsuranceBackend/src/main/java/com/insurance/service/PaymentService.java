package com.insurance.service;

import java.util.List;

import com.insurance.apiResponse.ApiResponse;
import com.insurance.dto.PaymentDto;
import com.insurance.entities.Payment;

public interface PaymentService {

	public ApiResponse pay(PaymentDto payment);
	public List<Payment> viewAllPayments();
	public ApiResponse findPaymentByPaymentId(long paymentId);
	public ApiResponse findPaymentByPolicyId(long policyId);
}
