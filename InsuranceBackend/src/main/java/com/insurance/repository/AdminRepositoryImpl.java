package com.insurance.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.insurance.dto.AdminDto;
import com.insurance.entities.Admin;
import com.insurance.entities.Claim;
import com.insurance.entities.User;

@Repository
public class AdminRepositoryImpl implements AdminRepository {

	@PersistenceContext
	EntityManager em;

	@Autowired
	PolicyRepository policyRepository;

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

	@Override
	@Transactional
	public long countOfclaimes() {
		// TODO Auto-generated method stub
		String jpql = "select COUNT(*) from Claim e where e.claimStatus='approved'";
		Query query = em.createQuery(jpql);
		long claimCount = (long) query.getSingleResult();
		return claimCount;
	}

	@Override
	@Transactional
	public long countOfpolicies() {
		String jpql = "select COUNT(p) from Policy p";
		Query query = em.createQuery(jpql);
		long countOfpolicies = (long) query.getSingleResult();
		return countOfpolicies;
	}

	@Override
	@Transactional
	public List<Claim> viewAllClaims() {
		String jpql = "select c from Claim c";
		Query query = em.createQuery(jpql);
		List<Claim> claims = query.getResultList();

		return claims;
	}

	@Override
	public long findexistingPolicies() {
		String jpql = "select COUNT(*) from Policy p where p.isExpired=0";
		Query query = em.createQuery(jpql);
		long countOfpolicies = (long) query.getSingleResult();
		return countOfpolicies;
	}

	@Override
	public Admin findadminByEmail(String email) {
		try {
			String jpql = "select a from Admin a where a.adminEmail=:email";
			Query query = em.createQuery(jpql);
			query.setParameter("email", email);
			Admin admin = (Admin) query.getSingleResult();
			return admin;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Claim updateClaimStatus(Claim claim) {
		return policyRepository.claimPolicy(claim);
	}

}
