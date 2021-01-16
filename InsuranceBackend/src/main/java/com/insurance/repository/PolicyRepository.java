package com.insurance.repository;

import java.util.List;

import com.insurance.apiResponse.ApiResponse;
import com.insurance.dto.ClaimDto;
import com.insurance.dto.PolicyDto;
import com.insurance.dto.RenewDto;
import com.insurance.entities.Claim;
import com.insurance.entities.Policy;

public interface PolicyRepository {

	public Policy buyPolicy(Policy policy);
	public Claim claimPolicy(Claim claim);
	public long renewPolicy(RenewDto renew);
	public Policy findPolicyByPolicyId(long policyId);
	public Claim findClaimById(long claimId);
	public Policy findPolicyByVehicleId(long vehicleId);
	public List<Policy> findPolicyByUserId(long userId);
	public List<ClaimDto> findClaimsbyUserId(long userId);
	public void deletePolicyById(long policyId);
}
