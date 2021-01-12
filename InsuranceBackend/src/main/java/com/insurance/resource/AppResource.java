package com.insurance.resource;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.insurance.apiResponse.ApiResponse;
import com.insurance.dto.LoginDto;
import com.insurance.dto.PolicyDto;
import com.insurance.service.VehicleService;
import com.insurance.entities.Policy;
import com.insurance.entities.User;
import com.insurance.entities.Vehicle;
import com.insurance.service.PolicyService;
import com.insurance.service.UserService;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
public class AppResource {

	@Autowired
	VehicleService vehicleService;
	
	@Autowired
	PolicyService policyService;

	@GetMapping(value = "/insurance/scrape/vehicleInfo")
	public ApiResponse vehicleInfo(@RequestParam String registrationNumber) throws IOException {
		return vehicleService.scrapeVehicleInfo(registrationNumber);
	}

	@Autowired
	UserService userService;

	@RequestMapping(value = "/addorUpdateUser", method = RequestMethod.POST)
	public ApiResponse addAUser(@RequestBody User user) {
		ApiResponse apiResponse = userService.signUpUser(user);
		return apiResponse;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ApiResponse login(@RequestBody LoginDto loginDto) {
		System.out.println(""+loginDto.getUserEmail());
		System.out.println(""+loginDto.getUserPassword());
		return userService.login(loginDto);
	}

	@RequestMapping(value = "/insurance/addOrUpdateVehicle", method = RequestMethod.POST)
	public ApiResponse addOrUpdateVehicleDetails(@RequestBody Vehicle vehicle) {

		return vehicleService.addOrUpdateVehicle(vehicle);
	}
	
	@PostMapping(value = "/insurance/buyPolicy")
	public ApiResponse buyPolicy(@RequestBody Policy policy) {

		return policyService.buyPolicy(policy);
	}
	
	@GetMapping(value = "/insurance/findPolicy")
	public ApiResponse buyPolicy(@RequestParam long policyNumber) {

		return policyService.findPolicyByPolicyNumber(policyNumber);
	}
}
