package com.insurance.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insurance.apiResponse.ApiResponse;
import com.insurance.entities.Vehicle;
import com.insurance.repository.VehicleRepository;

@Service
public class VehicleServiceImpl implements VehicleService {

	@Autowired
	VehicleRepository vehicleRepository;

	@Override
	public ApiResponse addOrUpdateVehicle(Vehicle vehicle) {

		Vehicle vehicleData = vehicleRepository.addOrUpdateVehicle(vehicle);
		if (vehicleData != null) {
			return new ApiResponse(200, "Vehicle Data is registered successfully", vehicleData);

		}
		return new ApiResponse(400, "Vehicle registration failed", null);
	}

	@Override
	public ApiResponse viewAllvehicles() {
		return null;
	}

	@Override
	public ApiResponse findVehicleByRegNo(String registration) {
		Vehicle storedVehicle = vehicleRepository.findVehicleByRegNo(registration);
		if (storedVehicle != null) {
			return new ApiResponse(200, "Vehicle data existed", storedVehicle);
		}
		return new ApiResponse(400, "No vehicle data is present", null);
	}

}
