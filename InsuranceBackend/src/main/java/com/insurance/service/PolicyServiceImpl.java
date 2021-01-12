package com.insurance.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insurance.apiResponse.ApiResponse;
import com.insurance.dto.RenewDto;
import com.insurance.entities.Policy;
import com.insurance.repository.PolicyRepository;

@Service
public class PolicyServiceImpl implements PolicyService {

	@Autowired
	PolicyRepository policyRepository;
	
	@Override
	public ApiResponse buyPolicy(Policy policy) {
		long generatedPolicyId = policyRepository.buyPolicy(policy);
		if(generatedPolicyId > 0) {
			return new ApiResponse(200, "Policy data is saved", null);
		}
		return new ApiResponse(400, "Policy data is not saved", null);
	}

	@Override
	public ApiResponse claimPolicy(long policyNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponse renewPolicy(RenewDto renew) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponse findPolicyByPolicyNumber(long policyNo) {
		Policy policyData = policyRepository.findPolicyByPolicyNumber(policyNo);
		if(policyData == null) {
			return new ApiResponse(400, "No policy found", null);
		}
		return new ApiResponse(400, "Policy Data found", policyData);
	}

}
