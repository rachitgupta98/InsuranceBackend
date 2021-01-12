package com.insurance.service;

import com.insurance.apiResponse.ApiResponse;
import com.insurance.dto.PolicyDto;
import com.insurance.dto.RenewDto;
import com.insurance.entities.Policy;

public interface PolicyService {

	public ApiResponse buyPolicy(PolicyDto policy);
	public ApiResponse claimPolicy(long policyNumber);
	public ApiResponse renewPolicy(RenewDto renew);
	public ApiResponse findPolicyByPolicyNumber(long policyNo);
}
