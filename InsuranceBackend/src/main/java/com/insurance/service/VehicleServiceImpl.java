package com.insurance.service;

import java.io.IOException;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.Connection.Method;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insurance.apiResponse.ApiResponse;
import com.insurance.dto.VehicleDto;
import com.insurance.entities.Vehicle;
import com.insurance.repository.VehicleRepository;

@Service
public class VehicleServiceImpl implements VehicleService {

	@Autowired
	VehicleRepository vehicleRepository;

	@Override
	public ApiResponse addOrUpdateVehicle(Vehicle vehicle) {
		
		long vehicleId = vehicleRepository.addOrUpdateVehicle(vehicle);
		if(vehicleId > 0) {
			return new ApiResponse(200, "Vehicle Data is registered successfully", null);

		}
		return new ApiResponse(400, "Vehicle registration failed", null);
	}

	@Override
	public ApiResponse viewAllvehicles() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponse findVehicleById(String registrationNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
