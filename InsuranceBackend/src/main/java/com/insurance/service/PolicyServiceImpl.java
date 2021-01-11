package com.insurance.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insurance.apiResponse.ApiResponse;
import com.insurance.dto.RenewDto;
import com.insurance.entities.Claim;
import com.insurance.entities.Policy;
import com.insurance.repository.PolicyRepository;

@Service
public class PolicyServiceImpl implements PolicyService {
	@Autowired
	PolicyRepository policyRepo;
	
	@Override
	public ApiResponse buyPolicy(Policy policy) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Claim claimPolicy(Claim claim) {
		// TODO Auto-generated method stub
		return policyRepo.claimPolicy(claim);
	}

	@Override
	public ApiResponse renewPolicy(RenewDto renew) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Policy findPolicyByPolicyNumber(long policyNumber) {
		return policyRepo.findPolicyByPolicyNumber(policyNumber);
		
	}

}
