package com.insurance.service;

import com.insurance.apiResponse.ApiResponse;
import com.insurance.entities.Admin;

public interface AdminService {
	public ApiResponse addOrUpdateAdmin(Admin admin);
	public ApiResponse findAdminByAdminId(long adminId);
}
