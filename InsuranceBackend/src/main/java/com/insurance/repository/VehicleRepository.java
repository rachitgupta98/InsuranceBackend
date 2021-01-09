package com.insurance.repository;

import java.util.List;

import com.insurance.entities.Vehicle;

public interface VehicleRepository {
	public long addOrUpdateVehicle(Vehicle vehicle);
	public List<Vehicle> viewAllvehicles();
	public Vehicle findVehicleById(String registrationNumber);
}
