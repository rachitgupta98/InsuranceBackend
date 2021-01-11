package com.insurance.resource;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.insurance.apiResponse.ApiResponse;
import com.insurance.service.VehicleService;

@CrossOrigin (origins = "http://localhost:4200", maxAge = 3600)
@RestController
public class AppResource {
	
	@Autowired
	VehicleService vehicleService;
	
	@GetMapping(value="/insurance/scrape/vehicleInfo")
	public ApiResponse vehicleInfo(@RequestParam String registrationNumber) throws IOException {
		return vehicleService.scrapeVehicleInfo(registrationNumber);
	}
	
}
