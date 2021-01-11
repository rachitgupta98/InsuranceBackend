package com.insurance.resource;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.insurance.apiResponse.ApiResponse;
import com.insurance.dto.LoginDto;
import com.insurance.service.VehicleService;

import com.insurance.entities.User;
import com.insurance.service.UserService;

@CrossOrigin (origins = "http://localhost:4200", maxAge = 3600)
@RestController
public class AppResource {
	
	@Autowired
	VehicleService vehicleService;
	
	@GetMapping(value="/insurance/scrape/vehicleInfo")
	public ApiResponse vehicleInfo(@RequestParam String registrationNumber) throws IOException {
		return vehicleService.scrapeVehicleInfo(registrationNumber);
	}
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value="/addorUpdateUser",method=RequestMethod.POST)
	public  ApiResponse addAUser(@RequestBody User user){
		ApiResponse apiResponse = userService.signUpUser(user);
		return apiResponse;
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
    public ApiResponse login(@RequestBody LoginDto loginDto){
	 
        return userService.login(loginDto);
 } 


}
	
	

