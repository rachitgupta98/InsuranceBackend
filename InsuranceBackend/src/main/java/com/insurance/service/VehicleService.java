package com.insurance.service;

import java.util.List;

import com.insurance.apiResponse.ApiResponse;
import com.insurance.entities.Vehicle;

public interface VehicleService {
	public ApiResponse addOrUpdateVehicle(Vehicle vehicle);
	public ApiResponse viewAllvehicles();
	public ApiResponse findVehicleById(String registrationNumber);
}
