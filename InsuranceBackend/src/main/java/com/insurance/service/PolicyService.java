package com.insurance.service;

import com.insurance.apiResponse.ApiResponse;
import com.insurance.dto.ClaimDto;
import com.insurance.dto.RenewDto;
import com.insurance.entities.Claim;
import com.insurance.entities.Policy;

public interface PolicyService {

	public ApiResponse buyPolicy(Policy policy);
	public ApiResponse claimPolicy(ClaimDto claimDto);
	public ApiResponse renewPolicy(RenewDto renew);
	public Policy findPolicyByPolicyNumber(long policyNumber);
}
