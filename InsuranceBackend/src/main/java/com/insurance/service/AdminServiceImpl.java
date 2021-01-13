package com.insurance.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insurance.apiResponse.ApiResponse;
import com.insurance.entities.Admin;
import com.insurance.repository.AdminRepository;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	AdminRepository adminRepository;
	
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

}
