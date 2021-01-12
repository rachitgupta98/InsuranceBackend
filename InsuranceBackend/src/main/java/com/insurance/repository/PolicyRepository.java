package com.insurance.repository;

import com.insurance.apiResponse.ApiResponse;
import com.insurance.dto.PolicyDto;
import com.insurance.dto.RenewDto;
import com.insurance.entities.Policy;

public interface PolicyRepository {

	public long buyPolicy(Policy policy);
	public long claimPolicy(long policyNumber);
	public long renewPolicy(RenewDto renew);
	public Policy findPolicyByPolicyNumber(long policyNumber);
}
