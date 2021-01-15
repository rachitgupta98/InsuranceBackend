package com.insurance.repository;

import java.util.List;

import com.insurance.apiResponse.ApiResponse;
import com.insurance.entities.Payment;

public interface PaymentRepository {

	public Payment pay(Payment payment);
	public List<Payment> viewAllPayments();
	public Payment findPaymentByPaymentId(long paymentId);
	public Payment findPaymentByPolicyId(long policyId);
}
