package com.insurance.repository;

import java.util.List;

import com.insurance.dto.AdminDto;
import com.insurance.entities.Admin;
import com.insurance.entities.Claim;
import com.insurance.entities.User;

public interface AdminRepository {
	public Admin addOrUpdateAdmin(Admin admin);
	public Admin findAdminByAdminId(long adminId);
	public long countOfpolicies();
	public long countOfclaimes();
	public Admin findadminByEmail(String email);
	public List<Claim> viewAllClaims();
	public long findexistingPolicies();
}
