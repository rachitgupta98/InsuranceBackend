package com.insurance.repository;

import com.insurance.entities.Admin;

public interface AdminRepository {
	public Admin addOrUpdateAdmin(Admin admin);
	public Admin findAdminByAdminId(long adminId);
}
