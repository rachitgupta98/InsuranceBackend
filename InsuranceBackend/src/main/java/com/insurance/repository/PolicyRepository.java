package com.insurance.repository;

import com.insurance.apiResponse.ApiResponse;
import com.insurance.dto.PolicyDto;
import com.insurance.dto.RenewDto;
import com.insurance.entities.Claim;
import com.insurance.entities.Policy;

public interface PolicyRepository {

	public Policy buyPolicy(Policy policy);
	public Claim claimPolicy(Claim claim);
	public long renewPolicy(RenewDto renew);
	public Policy findPolicyByPolicyId(long policyId);
}
