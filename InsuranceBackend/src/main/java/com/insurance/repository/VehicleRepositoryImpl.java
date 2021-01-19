package com.insurance.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.insurance.entities.Vehicle;
import com.insurance.model.VehicleModel;

@Repository
public class VehicleRepositoryImpl implements VehicleRepository {
	@PersistenceContext
	EntityManager em;

	@Transactional
	public Vehicle addOrUpdateVehicle(Vehicle vehicle) {
		Vehicle vehicleData = em.merge(vehicle);
		return vehicleData;
	}

	@Transactional
	public List<Vehicle> viewAllvehicles() {

		return null;
	}

	@Override
	@Transactional
	public Vehicle findVehicleByVehicleId(long vehicleId) {
		return em.find(Vehicle.class, vehicleId);
	}

	@Override
	@Transactional
	public Vehicle findVehicleByRegNo(String reg) {
		String jpql = "select v from Vehicle v where v.registrationNo=:reg";
		Query query = em.createQuery(jpql);
		query.setParameter("reg", reg);
		Vehicle v = null;
		try {
			v = (Vehicle) query.getSingleResult();
		} catch (NoResultException noResult) {
			return null;
		}
		return v;
	}

}
