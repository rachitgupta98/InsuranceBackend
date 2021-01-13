package com.insurance.resource;

import java.io.IOException;



import javax.servlet.http.HttpServletRequest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;

import org.springframework.ui.Model;
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
import com.insurance.service.EmailService;
import com.insurance.service.PolicyService;
import com.insurance.service.UserService;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
public class AppResource {

	@Autowired
	VehicleService vehicleService;
	
	@Autowired
	PolicyService policyService;

	@Autowired
	UserService userService;
		  
	@Autowired
	EmailService emailService;


	@RequestMapping(value = "/addorUpdateUser", method = RequestMethod.POST)
	public ApiResponse addAUser(@RequestBody User user) {
		ApiResponse apiResponse = userService.signUpUser(user);
		return apiResponse;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ApiResponse login(@RequestBody LoginDto loginDto) {

		return userService.login(loginDto);
	}

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
	
	@RequestMapping(value="/forgotPassword",method=RequestMethod.POST)
	public ApiResponse forgotPassword(@RequestBody User user){
		
		
			String subject="Password Reset link";
			String email = user.getUserEmail();
			String text="Hi "+user.getUserName()+"!!";
			emailService.sendEmail(email, text, subject);
			System.out.println("Email Sent.....");
		
		return userService.findByEmail(email);
	}
	@RequestMapping(value="/findUserBymail",method=RequestMethod.GET)
	public ApiResponse findUserByMail(@RequestBody User user){
		String email=user.getUserEmail();
		return userService.findByEmail(email);
		
	}
}

