package com.insurance.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.insurance.entities.Vehicle;

@Repository
public class VehicleRepositoryImpl implements VehicleRepository {

	@Override
	public long addOrUpdateVehicle(Vehicle vehicle) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Vehicle> viewAllvehicles() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vehicle findVehicleById(String registrationNumber) {
		// TODO Auto-generated method stub
		return null;
	}

}
