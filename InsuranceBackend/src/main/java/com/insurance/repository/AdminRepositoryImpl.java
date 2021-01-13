package com.insurance.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.insurance.entities.Admin;

@Repository
public class AdminRepositoryImpl implements AdminRepository {

	@PersistenceContext
	EntityManager em;

	@Transactional
	public Admin addOrUpdateAdmin(Admin admin) {
		Admin adminData = em.merge(admin);
		return adminData;
	}

	@Override
	public Admin findAdminByAdminId(long adminId) {
		Admin admin = em.find(Admin.class, adminId);
		return admin;
	}

}
