package com.insurance.service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insurance.apiResponse.ApiResponse;
import com.insurance.dto.ClaimDto;
import com.insurance.dto.PolicyDto;
import com.insurance.dto.RenewDto;
import com.insurance.entities.Admin;
import com.insurance.entities.Claim;
import com.insurance.entities.Policy;
import com.insurance.entities.User;
import com.insurance.entities.Vehicle;
import com.insurance.repository.AdminRepository;
import com.insurance.repository.PolicyRepository;
import com.insurance.repository.UserRepository;
import com.insurance.repository.VehicleRepository;

@Service
public class PolicyServiceImpl implements PolicyService {

	@Autowired
	PolicyRepository policyRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	VehicleRepository vehcileRepository;

	@Autowired
	AdminRepository adminRepository;

	@Override
	public ApiResponse buyPolicy(PolicyDto policy) {
		long userId = policy.getUserId();
		User user = userRepository.findUserById(userId);

		long vehicleId = policy.getVehicleId();
		Vehicle vehicle = vehcileRepository.findVehicleByVehicleId(vehicleId);

		Policy newpolicy = new Policy();
		if (policy.getPolicyId() > 0) {
			newpolicy.setPolicyId(policy.getPolicyId());
		}
		// newpolicy.setPolicyId(policy.getPolicyId());
		newpolicy.setUser(user);
		newpolicy.setVehicle(vehicle);
		newpolicy.setPlanType(policy.getPlanType());
		newpolicy.setPolicyStartDate(policy.getPolicyStartDate());
		newpolicy.setPolicyEndDate(policy.getPolicyEndDate());
		newpolicy.setPremiumAmount(policy.getPremiumAmount());
		newpolicy.setPurchaseDate(policy.getPurchaseDate());
		newpolicy.setExpired(policy.isExpired());
		newpolicy.setInsuranceAmount(policy.getInsuranceAmount());
		newpolicy.setPlanYear(policy.getPlanYear());
		Policy generatedPolicy = policyRepository.buyPolicy(newpolicy);

		if (generatedPolicy != null) {
			return new ApiResponse(200, "Policy data is saved", generatedPolicy);
		}
		return new ApiResponse(400, "Policy data is not saved", null);
	}

	@Override
	public Claim findClaimById(long claimId) {
		return policyRepository.findClaimById(claimId);
	}

	@Override
	public ApiResponse updateClaim(long claimId, String docFile) {
		Claim claim = findClaimById(claimId);
		claim.setDocumentFile(docFile);
		Claim updatedClaim = policyRepository.claimPolicy(claim);
		return new ApiResponse(200, "Doc File Uploaded Successfully", updatedClaim);
	}

	@Override
	public ApiResponse renewPolicy(long policyId, long userId) {
		Policy policy = policyRepository.findPolicyByPolicyId(policyId);
		if (policy == null) {
			return new ApiResponse(404, "no such policy exists", null);
		} else {
			User user = policy.getUser();
			if (user.getUserId() != userId) {
				return new ApiResponse(400, "you are not authorized to use this policy", null);
			}
			LocalDate date = LocalDate.now();
			System.out.println(policy.getPolicyEndDate());
			if (policy.getPolicyEndDate().compareTo(date) > 0) {
				return new ApiResponse(400, "Your policy has not expired yet", null);
			}
			return new ApiResponse(200, "Please enter the plan details to renew the policy", policy);
		}
	}

	@Override
	public ApiResponse claimPolicy(ClaimDto claimdto) {
		long claimForPolicyId = claimdto.getClaimForPolicyId();
		Policy policy = policyRepository.findPolicyByPolicyId(claimForPolicyId);
		long userId = claimdto.getUserId();
		System.out.println(userId);
		System.out.println(claimdto.getClaimForPolicyId());
		if (policy == null) {
			return new ApiResponse(400, "policy does not exists", null);
		} else {
			User user = policy.getUser();
			long userId1 = user.getUserId();
			System.out.println(userId1);
			LocalDate date1 = LocalDate.now();
			if (claimdto.getClaimAmount() > policy.getInsuranceAmount()) {
				return new ApiResponse(400, "Claim amount is more than Vehicle IDV", null);
			} else if (policy.getPolicyEndDate().compareTo(date1) < 0) {
				return new ApiResponse(400, "policy Expired need to be renewed", null);
			} else if (userId1 != userId) {
				return new ApiResponse(400, "you are not authorized to claim this policy", null);
			} else {
				List<Claim> claims = policy.getClaims();
				for (Claim claim : claims) {
					String s1 = claim.getClaimStatus();
					String s2 = "Pending from Admin";
					if (s1.compareTo(s2) == 0) {
						return new ApiResponse(400,
								"Your previous claims are still pending, so you cant apply for new claim", claim);
					}
				}
				Claim requestClaim = new Claim();
				requestClaim.setPolicy(policy);
				requestClaim.setUser(user);
				requestClaim.setClaimStatus("Pending from Admin");
				requestClaim.setClaimReason(claimdto.getClaimReason());
				requestClaim.setClaimAmount(claimdto.getClaimAmount());
				Claim claimData = policyRepository.claimPolicy(requestClaim);
				if (claimData != null) {
					return new ApiResponse(200, "Claim is registered", claimData);
				}
				return new ApiResponse(400, "Failed to claim policy", null);
			}
		}

	}

	@Override
	public ApiResponse findPolicyByVehicleId(long vehicleId) {
		Vehicle vehicle = vehcileRepository.findVehicleByVehicleId(vehicleId);
		Policy policy = vehicle.getPolicy();
		if (policy != null) {
			return new ApiResponse(200, "policy is existed", policy);
		}
		return new ApiResponse(400, "No policy existed", null);
	}

	@Override
	public ApiResponse findPolicyByUserId(long userId) {
		// TODO Auto-generated method stub
		List<Policy> policy = policyRepository.findPolicyByUserId(userId);
		if (policy != null) {
			return new ApiResponse(200, "policy is existed", policy);
		}
		return new ApiResponse(400, "No policy existed", null);
	}

	@Override
	public ApiResponse checkClaimStatus(long claimId) {
		// TODO Auto-generated method stub
		return null;
	}

}
