package com.insurance.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insurance.apiResponse.ApiResponse;
import com.insurance.dto.AdminDto;
import com.insurance.dto.ClaimApprovalDto;
import com.insurance.entities.Admin;
import com.insurance.entities.Claim;
import com.insurance.repository.AdminRepository;
import com.insurance.repository.PolicyRepository;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	AdminRepository adminRepository;
	@Autowired
	PolicyRepository policyRepository;
	
	@Override
	public ApiResponse addOrUpdateAdmin(Admin admin) {
		
		Admin adminData = adminRepository.addOrUpdateAdmin(admin);
		if(adminData !=null) {
			return new ApiResponse(200, null, admin);
		}
		return new ApiResponse(200, "failed", null);
	}

	@Override
	public ApiResponse findAdminByAdminId(long adminId) {
		Admin adminData = adminRepository.findAdminByAdminId(adminId);
		if(adminData != null) {
			return new ApiResponse(200, null, adminData);
		}
		return new ApiResponse(200, "failed", null);
	}

	@Override
	public ApiResponse countOfpolicies() {
		// TODO Auto-generated method stub
		long countOfpolicy=adminRepository.countOfpolicies();
		if(countOfpolicy!=0)
		{
			return new ApiResponse(200, null, countOfpolicy);
		}
		
		 return new ApiResponse(200, null, countOfpolicy);
	}

	@Override
	public ApiResponse countOfclaimes() {
		// TODO Auto-generated method stub
		long countOfclaims=adminRepository.countOfclaimes();
		if(countOfclaims!=0)
			return new ApiResponse(200, null, countOfclaims);
		return new ApiResponse(200, null, countOfclaims);
	}

	@Override
	public ApiResponse viewAllClaims() {
		// TODO Auto-generated method stub
		List<Claim>claims=adminRepository.viewAllClaims();
		if(claims!=null)
		{
			return new ApiResponse(200,"claims to be approved",claims);
		}
		
		return new ApiResponse(200,"No claims to approve",null);
		
		
	}

	@Override
	public ApiResponse findexistingPolicies() {
		// TODO Auto-generated method stub
		long existingPolicies=adminRepository.findexistingPolicies();
		if(existingPolicies!=0)
			return new ApiResponse(200, null, existingPolicies);
		return new ApiResponse(200, null, existingPolicies);
		
	}

	@Override
	public ApiResponse findadminByEmail(AdminDto admindto) {
		// TODO Auto-generated method stub
		Admin logadmin=adminRepository.findadminByEmail(admindto.getAdminEmail());
		System.out.println(logadmin.getAdminPassword());
		if(logadmin.getAdminPassword().equals(admindto.getAdminPassword()))
		{
			return new ApiResponse(200, "Login successful", logadmin);
		}
		return new ApiResponse(200, "Login Failed", null);
	}

	@Override
	public ApiResponse updateClaimStatus(ClaimApprovalDto claimapproval) {
		// TODO Auto-generated method stub
		System.out.println(claimapproval.getClaimId()+"claimID....");
		long claimId=claimapproval.getClaimId();
		long adminId=claimapproval.getAdminId();
		Admin admin=adminRepository.findAdminByAdminId(adminId);
		Claim claims=policyRepository.findClaimById(claimId);
		System.out.println(claims.getClaimId());
		String claimstatus=claimapproval.getClaimStatus();
		claims.setClaimStatus(claimstatus);
		claims.setAdmin(admin);
		policyRepository.claimPolicy(claims);
		
		if(claims!=null)
			return new ApiResponse(200,"updated succesfully",claims);
		return new ApiResponse(400,"updationFailed",null);
	}


}
