package com.insurance.repository;

import java.util.List;

import com.insurance.apiResponse.ApiResponse;
import com.insurance.entities.User;

public interface UserRepository {

	public long signUpUser(User user);
	public List<User> viewAllUsers();
	public User findUserById(long userId);
	public User deleteUser(long userId);
    public User findByEmail(String email);

}
