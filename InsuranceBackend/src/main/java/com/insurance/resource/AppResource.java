package com.insurance.resource;

import java.io.IOException;
import java.net.IDN;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.insurance.apiResponse.ApiResponse;
import com.insurance.dto.ClaimDto;
import com.insurance.dto.LoginDto;

import com.insurance.dto.PolicyDto;
import com.insurance.dto.ResetPasswordDto;
import com.insurance.service.VehicleService;




import com.insurance.entities.Admin;

import com.insurance.entities.User;
import com.insurance.entities.Vehicle;
import com.insurance.service.EmailService;
import com.insurance.service.AdminService;

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
    AdminService adminService;
 
    @Autowired
    EmailService emailService;

	@Autowired
	UserService userService;

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
		policy.setPurchaseDate(LocalDate.now());
		policy.setPolicyStartDate(LocalDate.now());
		policy.setPolicyEndDate(LocalDate.now().plusYears(policy.getPlanYear()));
		return policyService.buyPolicy(policy);
	}
	
	@GetMapping(value = "/findUserByEmail")
	public ApiResponse findUser(@RequestParam String userEmail) {

		return userService.findUserByEmail(userEmail);
	}
	
	@PostMapping(value = "/insurance/claimPolicy")
	public ApiResponse requestClaimPolicy(@RequestBody ClaimDto claimdto) {

		return policyService.claimPolicy(claimdto);
	}
	
	@PostMapping(value = "/insurance/addAdmin")
	public ApiResponse registerAdmin(@RequestBody Admin admin) {

		return adminService.addOrUpdateAdmin(admin);
	}
	
	@RequestMapping(value="/forgotPassword",method=RequestMethod.POST)
	public ApiResponse forgotPassword(@RequestBody User user){
		    
	       
			String subject="Here's the link to reset your password";
			String email = user.getUserEmail();
			String text="Hello,"
		            + "You have requested to reset your password."
		            + "Click the link to change your password:"
		            + "link=\"" +"http://localhost:4200/resetpassword"+ "\"Change my password";
		           
		           
			emailService.sendEmail(email, text, subject);
			System.out.println("Email Sent.....");
			return userService.findUserByEmail(email);
		
	}
	
	@RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
	public ApiResponse resetPassword(@RequestBody ResetPasswordDto resetPasswordDto) {
		
	  return  userService.updatePassword( resetPasswordDto);
	}


	@GetMapping(value="/insurance/findPolicyByVehicleId")
	public ApiResponse findPolicyByVehicleId(@RequestParam long vehicleId) {
		return policyService.findPolicyByVehicleId(vehicleId);
	}
	
	@GetMapping(value="/insurance/findVehicleByRegNo")
	public ApiResponse findVehicleByRegNo(@RequestParam String regist) {
		return vehicleService.findVehicleByRegNo(regist);
	}
	
	@RequestMapping(value = "/insurance/findUser/{userId}")
	public ApiResponse findUser(@PathVariable("userId") long userId) {
		System.out.println("entering");
		return userService.findUserById(userId);
	}
	
	@RequestMapping(value="/insurance/policies/{userId}")
	public ApiResponse findPolicybyuserId(@PathVariable("userId") long userId)
	{
		return policyService.findPolicyByUserId(userId);
	}
}
