package com.insurance.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.insurance.entities.Payment;

@Repository
public class PaymentRepositoryImpl implements PaymentRepository {

	@PersistenceContext
	EntityManager em;
	
	@Override
	@Transactional
	public Payment pay(Payment payment) {
		// TODO Auto-generated method stub
		return em.merge(payment);
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
