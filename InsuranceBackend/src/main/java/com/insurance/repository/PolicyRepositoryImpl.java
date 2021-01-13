

package com.insurance.repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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
		// TODO Auto-generated method stub
		System.out.println(policyNumber);
		try {
		String jpql = "select p from Policy p where p.policyNumber=policyNumber";
		TypedQuery<Policy> query = em.createQuery(jpql, Policy.class);
		
		return query.getSingleResult();
		}catch(NoResultException nre){
			return null;
		}
		
	}
	@Transactional
	public long renewPolicy(RenewDto renew) {
		
		return 0;
	}

	@Transactional
	public Claim findClaimById(long claimId) {
		return em.find(Claim.class, claimId);
	}

}
