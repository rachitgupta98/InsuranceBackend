


package com.insurance.resource;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.insurance.apiResponse.ApiResponse;
import com.insurance.dto.ClaimDto;
import com.insurance.dto.LoginDto;
import com.insurance.dto.PolicyDto;
import com.insurance.entities.Claim;
import com.insurance.entities.Policy;
import com.insurance.entities.User;
import com.insurance.entities.Vehicle;
import com.insurance.service.PolicyService;
import com.insurance.service.UserService;
import com.insurance.service.VehicleService;

@CrossOrigin (origins = "http://localhost:4200", maxAge = 3600)
@RestController
public class AppResource {
	@Autowired
	PolicyService policyService;
	@Autowired 
	UserService userService;
	
	@Autowired
	VehicleService vehicleService;

	@GetMapping(value = "/insurance/scrape/vehicleInfo")
	public ApiResponse vehicleInfo(@RequestParam String registrationNumber) throws IOException {
		return vehicleService.scrapeVehicleInfo(registrationNumber);
	}

	

	@RequestMapping(value = "/addorUpdateUser", method = RequestMethod.POST)
	public ApiResponse addAUser(@RequestBody User user) {
		
		ApiResponse apiResponse = userService.signUpUser(user);
		return apiResponse;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ApiResponse login(@RequestBody LoginDto loginDto) {

		return userService.login(loginDto);
	}

	/*
	 * @RequestMapping(value="/findPolicyBynumber",method=RequestMethod.GET) public
	 * Policy findPolicy(@RequestParam long policyNumber) { return
	 * policyService.findPolicyByPolicyNumber(policyNumber); }
	 */
	@RequestMapping(value = "/insurance/addOrUpdateVehicle", method = RequestMethod.POST)
	public ApiResponse addOrUpdateVehicleDetails(@RequestBody Vehicle vehicle) {

		return vehicleService.addOrUpdateVehicle(vehicle);
	}
	
	@PostMapping(value = "/insurance/buyPolicy")
	public ApiResponse buyPolicy(@RequestBody PolicyDto policy) {

		return policyService.buyPolicy(policy);
	}
	
	@GetMapping(value = "/insurance/findPolicy")
	public ApiResponse buyPolicy(@RequestParam long policyNumber) {

		return policyService.findPolicyByPolicyNumber(policyNumber);
	}
	
	@RequestMapping(value = "/claimPolicy",method = RequestMethod.POST)
	public ApiResponse claimPolicy(@RequestBody ClaimDto claimDto) {
		//System.out.println(claimDto.getClaimAmount()+" "+claimDto.getClaimReason()+" "+claimDto.getClaimForPolicyNumber()+" "+claimDto.getUserId());
		ApiResponse apiResponse=policyService.claimPolicy(claimDto);
		return apiResponse;
	}
	
}





