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
		// TODO Auto-generated method stub
		User newUser=em.merge(user);
		return newUser.getUserId();
	}

	@Transactional
	public List<User> viewAllUsers() {
		String jpql="select u from User u";
		Query query=em.createQuery(jpql);
		List<User> users=query.getResultList();
		return users;
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
