package com.insurance.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.insurance.apiResponse.ApiResponse;
import com.insurance.entities.User;

@Repository
public class UserRepositoryImpl implements UserRepository {

	@Override
	public long signUpUser(User user) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<User> viewAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findUserById(long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User deleteUser(long userId) {
		// TODO Auto-generated method stub
		return null;
	}

}
