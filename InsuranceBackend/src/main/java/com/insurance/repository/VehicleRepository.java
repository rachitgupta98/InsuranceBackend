package com.insurance.repository;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.insurance.dto.VehicleDto;
import com.insurance.entities.Vehicle;
import com.insurance.model.VehicleModel;

public interface VehicleRepository {
	public long addOrUpdateVehicle(Vehicle vehicle);
	public List<Vehicle> viewAllvehicles();
	public Vehicle findVehicleById(String registrationNumber);
	public Vehicle findVehicleByVehicleId(long vehicleId);
}
