package com.insurance.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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

	@Transactional
	public long buyPolicy(Policy policy) {
		Policy newPolicy=em.merge(policy);
		return newPolicy.getPolicyId();
	}

	@Transactional
	public Claim claimPolicy(Claim claim) {
		Claim newClaim=em.merge(claim);
		return newClaim;
	}
	@Transactional
	public Policy findPolicyByPolicyNumber(long policyNumber) {
		String jpql="select p from policy p where p.policyNumber=:policyNumber";
		Query query=em.createQuery(jpql);
		query.setParameter("policyNumber", policyNumber);
		Policy policy=(Policy)query.getSingleResult();
		return policy;
	}
	@Transactional
	public long renewPolicy(RenewDto renew) {
		
		return 0;
	}

}
