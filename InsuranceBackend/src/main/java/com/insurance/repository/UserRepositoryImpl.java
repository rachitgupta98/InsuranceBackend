package com.insurance.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.insurance.apiResponse.ApiResponse;
import com.insurance.entities.User;

@Repository
public class UserRepositoryImpl implements UserRepository {

	@PersistenceContext
	EntityManager em;

	@Transactional
	public long signUpUser(User user) {
		return 0;
	}

	@Transactional
	public List<User> viewAllUsers() {
		
		return null;
	}

	@Override
	public User findUserById(long userId) {
		return null;
	}

	@Override
	public User deleteUser(long userId) {
		return null;
	}

}
