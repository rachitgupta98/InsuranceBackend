
package com.insurance.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.insurance.apiResponse.ApiResponse;
import com.insurance.dto.RenewDto;
import com.insurance.entities.Claim;
import com.insurance.entities.Policy;

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
		return em.find(Policy.class, policyId);
		
	}
	@Transactional
	public Claim findClaimById(long claimId) {
		return em.find(Claim.class, claimId);
	}

	@Override
	@Transactional
	public Policy buyPolicy(Policy policy) {
		Policy policyData = em.merge(policy);
		return policyData;
	}

	@Override
	@Transactional
	public Claim claimPolicy(Claim claim) {
		Claim claimData = em.merge(claim);
		return claimData;
	}

	

}
