package com.insurance.repository;

import java.util.List;

import com.insurance.apiResponse.ApiResponse;
import com.insurance.entities.User;

public interface UserRepository {

	public long signUpUser(User user);
	public long viewAllUsers();
	public User findUserById(long userId);
	public User deleteUser(long userId);
    public User findByEmail(String email);
    public User save(User user);
}
