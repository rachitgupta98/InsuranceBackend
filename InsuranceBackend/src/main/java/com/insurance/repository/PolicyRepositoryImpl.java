package com.insurance.repository;

import org.springframework.stereotype.Repository;

import com.insurance.apiResponse.ApiResponse;
import com.insurance.dto.RenewDto;
import com.insurance.entities.Policy;

@Repository
public class PolicyRepositoryImpl implements PolicyRepository {

	@Override
	public long buyPolicy(Policy policy) {
		// TODO Auto-generated method stub
		return 0;
	}

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

}
