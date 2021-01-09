package com.insurance.service;

import com.insurance.apiResponse.ApiResponse;
import com.insurance.dto.RenewDto;
import com.insurance.entities.Policy;

public interface PolicyService {

	public ApiResponse buyPolicy(Policy policy);
	public ApiResponse claimPolicy(long policyNumber);
	public ApiResponse renewPolicy(RenewDto renew);
}
