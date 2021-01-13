package com.insurance.service;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insurance.apiResponse.ApiResponse;
import com.insurance.dto.PolicyDto;
import com.insurance.dto.RenewDto;
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
	UserRepository userRepository;
	
	@Autowired
	VehicleRepository vehcileRepository;
	
	@Override
	public ApiResponse buyPolicy(PolicyDto policy) {
		//int boundedRandomValue = ThreadLocalRandom.current().nextInt(0, 100);
		
		long userId = policy.getUserId();
		User user = userRepository.findUserById(userId);
		
		long vehicleId = policy.getVehicleId();
		Vehicle vehicle = vehcileRepository.findVehicleByVehicleId(vehicleId);
		
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
	public ApiResponse claimPolicy(long policyNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponse renewPolicy(RenewDto renew) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponse findPolicyByPolicyNumber(long policyNo) {
		Policy policyData = policyRepository.findPolicyByPolicyNumber(policyNo);
		if(policyData == null) {
			return new ApiResponse(400, "No policy found", null);
		}
		return new ApiResponse(400, "Policy Data found", policyData);
	}
    

}
