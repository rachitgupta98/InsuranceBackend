

package com.insurance.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insurance.apiResponse.ApiResponse;
import com.insurance.dto.ClaimDto;
import com.insurance.dto.PolicyDto;
import com.insurance.dto.RenewDto;
import com.insurance.entities.Claim;
import com.insurance.entities.Policy;
import com.insurance.entities.User;
import com.insurance.entities.Vehicle;
import com.insurance.repository.PolicyRepository;
import com.insurance.repository.UserRepository;
import com.insurance.repository.VehicleRepository;

@Service
public class PolicyServiceImpl implements PolicyService {
	@Autowired
	PolicyRepository policyRepository;
	@Autowired
	UserService userService;
	@Autowired
	UserRepository userRepository;
	@Autowired
	VehicleRepository vehicleRepository;
	
	
	

	@Override
	public ApiResponse claimPolicy(ClaimDto claimDto) {
		ApiResponse api=findPolicyByPolicyNumber(claimDto.getClaimForPolicyNumber());
		Policy policy=(Policy)api.getResult();
		
		if(api.getResult()==null) {
			System.out.println("policy not found");
			ApiResponse apiResponse=new ApiResponse(404,"policy not found",null);
			return apiResponse;
		}else {
			System.out.println(policy.getUser());
			User user1=policy.getUser();
			System.out.println(user1.getUserName());
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
			  ApiResponse(404,"User not valid for this particular policy",null);
			  return apiResponse; 
			  }
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
				ApiResponse apiResponse=new ApiResponse(200, "Claim submitted, pending from the back end", claim);
				return apiResponse;
			} 
		}
		
	}

	@Override
	public ApiResponse renewPolicy(RenewDto renew) {
		// TODO Auto-generated method stub
		return null;
	}
	public ApiResponse buyPolicy(PolicyDto policy) {
		//int boundedRandomValue = ThreadLocalRandom.current().nextInt(0, 100);
		
		long userId = policy.getUserId();
		User user = userRepository.findUserById(userId);
		
		long vehicleId = policy.getVehicleId();
		Vehicle vehicle = vehicleRepository.findVehicleByVehicleId(vehicleId);
		
		Policy newpolicy = new Policy();
		newpolicy.setUser(user);
		newpolicy.setVehicle(vehicle);
		newpolicy.setPolicyNumber(policy.getPolicyNumber());
		newpolicy.setPlanType(policy.getPlanType());
		newpolicy.setPolicyStartDate(policy.getPolicyStartDate());
		newpolicy.setPolicyEndDate(policy.getPolicyEndDate());
		newpolicy.setPremiumAmount(policy.getPremiumAmount());
		newpolicy.setPurchaseDate(policy.getPurchaseDate());
		newpolicy.setExpired(policy.isExpired());
		newpolicy.setInsuranceAmount(policy.getInsuranceAmount());
		
		long generatedPolicyId = policyRepository.buyPolicy(newpolicy);
		
		if(generatedPolicyId > 0) {
			return new ApiResponse(200, "Policy data is saved", null);
		}
		return new ApiResponse(400, "Policy data is not saved", null);
	}
	@Override
	public ApiResponse findPolicyByPolicyNumber(long policyNo) {
		System.out.println(policyNo);
		Policy policyData = policyRepository.findPolicyByPolicyNumber(policyNo);
		/*
		 * System.out.println(policyData.getUser()); System.out.println(policyData);
		 */
		System.out.println(policyData);
		if(policyData == null) {
			return new ApiResponse(400, "No policy found", null);
		}
		return new ApiResponse(200, "Policy Data found", policyData);
	}

	@Override
	public Claim findClaimById(long claimId) {
		return policyRepository.findClaimById(claimId);
		//return null;
	}

	@Override
	public ApiResponse updateClaim(long claimId, String docFile) {
		// TODO Auto-generated method stub
		Claim claim=findClaimById(claimId);
		claim.setDocumentFile(docFile);
		Claim updatedClaim=policyRepository.claimPolicy(claim);
		return new ApiResponse(200, "Doc File Uploaded Successfully", updatedClaim);
	}

}

