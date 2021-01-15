package com.insurance.service;

import java.util.List;

import com.insurance.apiResponse.ApiResponse;
import com.insurance.dto.ClaimDto;
import com.insurance.dto.PolicyDto;
import com.insurance.dto.RenewDto;
import com.insurance.entities.Claim;
import com.insurance.entities.Policy;

public interface PolicyService {

	public ApiResponse buyPolicy(PolicyDto policy);
	public ApiResponse claimPolicy(ClaimDto claimdto);
	public ApiResponse renewPolicy(long policyId,long userId);
	public Claim findClaimById(long claimId);
	public ApiResponse updateClaim(long claimId,String docFile);
	//public ApiResponse findPolicyByPolicyId(long policyNo);
	public ApiResponse findPolicyByVehicleId(long vehicleId);
	public ApiResponse findPolicyByUserId(long userId);
	public ApiResponse findClaimsbyUserId(long userId);
}
