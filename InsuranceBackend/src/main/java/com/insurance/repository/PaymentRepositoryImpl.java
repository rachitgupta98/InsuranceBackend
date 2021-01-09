package com.insurance.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.insurance.entities.Payment;

@Repository
public class PaymentRepositoryImpl implements PaymentRepository {

	@Override
	public long pay(Payment payment) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Payment> viewAllPayments() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Payment findPaymentByPaymentId(long paymentId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Payment findPaymentByPolicyId(long policyId) {
		// TODO Auto-generated method stub
		return null;
	}

}
