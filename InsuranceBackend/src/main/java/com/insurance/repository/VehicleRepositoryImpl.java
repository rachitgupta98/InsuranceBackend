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
		// TODO Auto-generated method stub
		Vehicle newVehicle=em.merge(vehicle);
		return newVehicle.getVehicleId();
	}

	@Transactional
	public List<Vehicle> viewAllvehicles() {
		// TODO Auto-generated method stub
		String jpql="select v from Vehicle v";
		Query query=em.createQuery(jpql);
		
		List<Vehicle> vehicles=query.getResultList();
		return vehicles;
	}

	@Transactional
	public Vehicle findVehicleById(String registrationNumber) {
		// TODO Auto-generated method stub
		String jpql="select v from Vehicle v where v.registrationNumber=:registrationNumber";
		Query query=em.createQuery(jpql);
		query.setParameter("registrationNumber", registrationNumber);
		Vehicle vehicle=(Vehicle)query.getSingleResult();
		return vehicle;
	}

}
