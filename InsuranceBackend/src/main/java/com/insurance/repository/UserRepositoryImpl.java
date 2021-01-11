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
		User registerd_user = em.merge(user);

		return registerd_user.getUserId();
	}

	@Transactional
	public List<User> viewAllUsers() {
		String jpql = "select u from tbl_userData u";
		Query query = em.createQuery(jpql);
		List<User> users = query.getResultList();
		return users;
	}

	@Override
	public User findUserById(long userId) {
		User user = em.find(User.class, userId);
		return user;
	}

	@Override
	public User deleteUser(long userId) {
		User user = em.find(User.class, userId);
		em.remove(user);
		return user;
	}

	@Override
	public User findByEmail(String email) {
		String jpql="select e from User e where e.userEmail=:email";
		Query query = em.createQuery(jpql);
		query.setParameter("email", email);
		User user=(User) query.getSingleResult();
		System.out.println("user logged in");
		return user;
	}

}
