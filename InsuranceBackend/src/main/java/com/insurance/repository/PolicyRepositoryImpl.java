
package com.insurance.repository;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.insurance.apiResponse.ApiResponse;
import com.insurance.dto.RenewDto;
import com.insurance.entities.Claim;
import com.insurance.entities.Policy;

import com.insurance.entities.User;

import com.insurance.entities.Vehicle;


@Repository
public class PolicyRepositoryImpl implements PolicyRepository {

	@PersistenceContext
	EntityManager em;
	
	
	@Override
	public long renewPolicy(RenewDto renew) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	@Transactional
	public Policy findPolicyByPolicyId(long policyId) {
		// TODO Auto-generated method stub
//		String jpql = "select p from Policy p where p.policyId=policyId";
//		TypedQuery<Policy> query = em.createQuery(jpql, Policy.class);
		try {
		Policy policy = em.find(Policy.class,policyId);
		
		return policy;}
		catch(NullPointerException nre) {
			return null;
		}
		
	}
	
	@Transactional
	public List<Policy> findPolicyByUserId(long userId)
	{
		String jpql="select p from Policy p where p.user.userId=:userId";
		Query query = em.createQuery(jpql);
		query.setParameter("userId", userId);
		List<Policy> policies=query.getResultList();
		for(Policy policy:policies) {
			LocalDate date=LocalDate.now();
			if(date.compareTo(policy.getPolicyEndDate())>0) {
				policy.setExpired(true);
				em.merge(policy);
			}
		}
		jpql="select p from Policy p where p.user.userId=:userId";
		query = em.createQuery(jpql);
		query.setParameter("userId", userId);
		List<Policy> updatedpolicies=query.getResultList();
		return updatedpolicies;
		
	}
	@Transactional
	public Policy buyPolicy(Policy policy) {
		Policy policyData = em.merge(policy);
		return policyData;
	}

	
	@Transactional
	public Claim claimPolicy(Claim claim) {
		Claim claimData = em.merge(claim);
		return claimData;
	}

	@Override
	@Transactional
	public Policy findPolicyByVehicleId(long vehicleId) {
		String jpql = "select p from Policy p where p.vehicle=:vehicleId";
		Query query = em.createQuery(jpql);
		query.setParameter("vehicleId", vehicleId);
		return (Policy) query.getSingleResult();
		
	}

	@Transactional
	public Claim findClaimById(long claimId) {
		return em.find(Claim.class, claimId);

	}

	@Override
	@Transactional
	public void deletePolicyById(long policyId) {
		System.out.println(policyId);
		Policy policy=em.find(Policy.class, policyId);
		System.out.println(policy);
		em.remove(policy);
		
	}

	

}

