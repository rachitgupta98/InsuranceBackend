package com.insurance.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.insurance.entities.Vehicle;

@Repository
public class VehicleRepositoryImpl implements VehicleRepository {
	@PersistenceContext
	EntityManager em;

	@Transactional
	public long addOrUpdateVehicle(Vehicle vehicle) {
		return 0;
	}

	@Transactional
	public List<Vehicle> viewAllvehicles() {

		return null;
	}

	@Transactional
	public Vehicle findVehicleById(String registrationNumber) {

		return null;
	}

}
