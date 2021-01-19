package com.insurance.repository;

import java.util.List;
import java.time.LocalDate;
import java.time.Month;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.hibernate.Session;
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
	public long viewAllUsers() {
		String jpql = "select COUNT(*) from User u";
		Query query = em.createQuery(jpql);
		long users = (long) query.getSingleResult();
		return users;
	}

	@Transactional
	public User findUserById(long userId) {
		User user = em.find(User.class, userId);
		return user;
	}

	@Transactional
	public User deleteUser(long userId) {
		User user = em.find(User.class, userId);
		em.remove(user);
		return user;
	}

	@Transactional
	public User findByEmail(String email) {

		User user;
		try {
			String jpql = "select e from User e where e.userEmail=:email";
			Query query = em.createQuery(jpql);
			query.setParameter("email", email);
			user = (User) query.getSingleResult();
			return user;
		} catch (Exception e) {
			return null;
		}

	}

	public User save(User user) {
		Session session = em.unwrap(Session.class);
		session.persist(user);
		return user;
	}

}
