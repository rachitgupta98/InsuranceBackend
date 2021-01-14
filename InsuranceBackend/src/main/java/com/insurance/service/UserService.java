package com.insurance.service;

import java.util.List;

import com.insurance.apiResponse.ApiResponse;
import com.insurance.dto.LoginDto;
import com.insurance.entities.User;

public interface UserService {

	public ApiResponse signUpUser(User user);
	public ApiResponse viewAllUsers();
	public ApiResponse findUserById(long userId);
	public ApiResponse deleteUser(long userId);
	public ApiResponse login(LoginDto loginDto);

}
