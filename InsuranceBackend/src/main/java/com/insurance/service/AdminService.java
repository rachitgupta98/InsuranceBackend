package com.insurance.service;

import java.util.List;

import com.insurance.apiResponse.ApiResponse;
import com.insurance.dto.AdminDto;
import com.insurance.dto.ClaimApprovalDto;
import com.insurance.entities.Admin;
import com.insurance.entities.Claim;

public interface AdminService {
	public ApiResponse addOrUpdateAdmin(Admin admin);
	public ApiResponse findAdminByAdminId(long adminId);
	public ApiResponse findadminByEmail(AdminDto admindto);
	public ApiResponse countOfpolicies();
	public ApiResponse countOfclaimes();
	public ApiResponse viewAllClaims();
	public ApiResponse findexistingPolicies();
	public ApiResponse updateClaimStatus(ClaimApprovalDto claimapproval);
}
