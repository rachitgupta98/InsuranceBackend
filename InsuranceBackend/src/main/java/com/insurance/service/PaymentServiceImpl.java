package com.insurance.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insurance.apiResponse.ApiResponse;
import com.insurance.dto.PaymentDto;
import com.insurance.entities.Payment;
import com.insurance.entities.Policy;
import com.insurance.entities.User;
import com.insurance.repository.PaymentRepository;
import com.insurance.repository.PolicyRepository;
import com.insurance.repository.UserRepository;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	PolicyRepository policyRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PaymentRepository paymentRepository;
	
	@Override
	public ApiResponse pay(PaymentDto payment) {
		// TODO Auto-generated method stub
		long userId = payment.getUserId();
		User user = userRepository.findUserById(userId);
		
		long policyId = payment.getPolicyId();
		Policy policy = policyRepository.findPolicyByPolicyId(policyId);
		
		Payment makePayment = new Payment();
		
		makePayment.setPolicy(policy);
		makePayment.setUser(user);
		makePayment.setPaymentAmount(payment.getPaymentAmount());
		makePayment.setPaymentDate(LocalDate.now());
		
		Payment payResult = paymentRepository.pay(makePayment);
		if(payResult != null) {
			return new ApiResponse(200,"Payment Done",payResult);
		}
		return new ApiResponse(400,"Payment Failed",null);
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
