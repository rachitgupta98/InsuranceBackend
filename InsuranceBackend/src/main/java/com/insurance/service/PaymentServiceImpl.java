package com.insurance.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.insurance.apiResponse.ApiResponse;
import com.insurance.entities.Payment;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Override
	public ApiResponse pay(Payment payment) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Payment> viewAllPayments() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponse findPaymentByPaymentId(long paymentId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponse findPaymentByPolicyId(long policyId) {
		// TODO Auto-generated method stub
		return null;
	}

}
