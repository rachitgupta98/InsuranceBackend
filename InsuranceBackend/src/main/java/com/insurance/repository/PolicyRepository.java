package com.insurance.repository;

import com.insurance.apiResponse.ApiResponse;
import com.insurance.dto.RenewDto;
import com.insurance.entities.Claim;
import com.insurance.entities.Policy;

public interface PolicyRepository {

	public long buyPolicy(Policy policy);
	public Claim claimPolicy(Claim claim);
	public long renewPolicy(RenewDto renew);
	public Policy findPolicyByPolicyNumber(long policyNumber);
}
