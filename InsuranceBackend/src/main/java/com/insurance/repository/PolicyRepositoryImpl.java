package com.insurance.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.insurance.apiResponse.ApiResponse;
import com.insurance.dto.RenewDto;
import com.insurance.entities.Policy;

@Repository
public class PolicyRepositoryImpl implements PolicyRepository {

	@PersistenceContext
	EntityManager em;
	
	

	@Override
	public long claimPolicy(long policyNumber) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long renewPolicy(RenewDto renew) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	@Transactional
	public Policy findPolicyByPolicyNumber(long policyNumber) {
		// TODO Auto-generated method stub
		String jpql = "select p from Policy p where p.policyNumber=policyNumber";
		TypedQuery<Policy> query = em.createQuery(jpql, Policy.class);
		
		return query.getSingleResult();
		
	}

	@Override
	@Transactional
	public long buyPolicy(Policy policy) {
		Policy policyData = em.merge(policy);
		return policyData.getPolicyId();
	}

	

}
