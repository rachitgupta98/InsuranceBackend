
package com.insurance.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insurance.apiResponse.ApiResponse;
import com.insurance.dto.ClaimDto;
import com.insurance.dto.RenewDto;
import com.insurance.entities.Claim;
import com.insurance.entities.Policy;
import com.insurance.entities.User;
import com.insurance.repository.PolicyRepository;

@Service
public class PolicyServiceImpl implements PolicyService {
	@Autowired
	PolicyRepository policyRepository;
	@Autowired
	UserService userService;
	
	
	@Override
	public ApiResponse buyPolicy(Policy policy) {
		long generatedPolicyId = policyRepository.buyPolicy(policy);
		if(generatedPolicyId > 0) {
			return new ApiResponse(200, "Policy data is saved", null);
		}
		return new ApiResponse(400, "Policy data is not saved", null);
	}

	@Override
	public ApiResponse claimPolicy(ClaimDto claimDto) {
		System.out.println(claimDto.getClaimForPolicyNumber());
		ApiResponse api=findPolicyByPolicyNumber(claimDto.getClaimForPolicyNumber());
		Policy policy=(Policy)api.getResult();
		//System.out.println(api.getResult());
		//System.out.println(policy.getPlanType());
		//Policy policy=findPolicyByPolicyNumber(12321);
		if(api.getResult()==null) {
			System.out.println("policy not found");
			ApiResponse apiResponse=new ApiResponse(404,"policy not found",null);
			return apiResponse;
		}else {
			User user1=policy.getUser();
			long userId=user1.getUserId();
			java.util.Date date=new java.util.Date();
			if(claimDto.getClaimAmount()>policy.getInsuranceAmount()) {
				ApiResponse apiResponse=new ApiResponse(404,"Claim amount is more than Vehicle IDV",null);
				return apiResponse;
			}
			else if(policy.getPolicyEndDate().compareTo(date)<0) {
				ApiResponse apiResponse=new ApiResponse(404,"Policy has expired",null);
				return apiResponse;
			}
			  else if(userId!=claimDto.getUserId()) { ApiResponse apiResponse=new
			  ApiResponse(404,"User not valid for this particular policy",null); return
			  apiResponse; }
			 
			else {
				User user=userService.findUserById(claimDto.getUserId());
				Claim claim=new Claim();
				claim.setClaimAmount(claimDto.getClaimAmount());
				claim.setClaimForPolicyNumber(claimDto.getClaimForPolicyNumber());
				claim.setClaimReason(claimDto.getClaimReason());
				claim.setPolicy(policy);
				claim.setClaimStatus("Pending From Admin");
				claim.setUser(user);
				claim=policyRepository.claimPolicy(claim);
				ApiResponse apiResponse=new ApiResponse(200, "Claim submitted pending from the back end", claim);
				return apiResponse;
			} 
		}
		
	}

	@Override
	public ApiResponse renewPolicy(RenewDto renew) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponse findPolicyByPolicyNumber(long policyNo) {
		Policy policyData = policyRepository.findPolicyByPolicyNumber(policyNo);
		/*
		 * System.out.println(policyData.getUser()); System.out.println(policyData);
		 */
		if(policyData.getUser() == null) {
			return new ApiResponse(400, "No policy found", null);
		}
		return new ApiResponse(200, "Policy Data found", policyData);
	}

}

