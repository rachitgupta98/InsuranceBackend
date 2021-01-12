package com.insurance.repository;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Repository;

import com.insurance.dto.VehicleDto;
import com.insurance.entities.Vehicle;
import com.insurance.model.VehicleModel;

@Repository
public class VehicleRepositoryImpl implements VehicleRepository {
	@PersistenceContext
	EntityManager em;

	@Transactional
	public long addOrUpdateVehicle(Vehicle vehicle) {
		Vehicle vehicleData = em.merge(vehicle);
		return vehicleData.getVehicleId();
	}

	@Transactional
	public List<Vehicle> viewAllvehicles() {

		return null;
	}

	@Transactional
	public Vehicle findVehicleById(String registrationNumber) {

		return null;
	}

	

	@Override
	@Transactional
	public Vehicle findVehicleByVehicleId(long vehicleId) {
		// TODO Auto-generated method stub
		return em.find(Vehicle.class, vehicleId);
	}

	

}
