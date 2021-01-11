package com.insurance.resource;

import java.time.LocalDate;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.insurance.dto.ClaimDto;
import com.insurance.entities.Claim;
import com.insurance.entities.Policy;
import com.insurance.entities.User;
import com.insurance.service.PolicyService;
import com.insurance.service.UserService;

@CrossOrigin (origins = "http://localhost:4200", maxAge = 3600)
@RestController
public class AppResource {
	@Autowired
	PolicyService policyService;
	@Autowired 
	UserService userService;
	
	@RequestMapping(value = "/claimPolicy",method = RequestMethod.POST)
	public Claim claimPolicy(ClaimDto claimDto) {
		Policy policy=policyService.findPolicyByPolicyNumber(claimDto.getPolicyNumber());
		java.util.Date date=new java.util.Date();
		// date2=(LocalDate)policy.getPolicyEndDate();
		if(claimDto.getClaimAmount()>policy.getInsuranceAmount()||policy.getPolicyEndDate().compareTo(date)>0) {
			return null;
		}else {
			User user=userService.findUserById(claimDto.getUserId());
			Claim claim=new Claim();
			claim.setClaimAmount(claimDto.getClaimAmount());
			claim.setClaimForPolicyNumber(claimDto.getPolicyNumber());
			claim.setClaimReason(claimDto.getClaimReason());
			claim.setPolicy(policy);
			claim.setClaimStatus("Pending From Admin");
			claim.setUser(user);
			policyService.claimPolicy(claim);
		}
		return null;
	}
}
