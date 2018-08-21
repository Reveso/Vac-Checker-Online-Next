package com.lukasrosz.vaccheckeronline.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lukasrosz.vaccheckeronline.accounts.entity.User;


@Repository
public class UserDAOImpl implements UserDAO {
	
	@Autowired
	private SessionFactory usersSessionFactory;

	@Override
	public List<User> getUsersList() {
		
		Session session = usersSessionFactory.getCurrentSession();
		
		Query<User> usersQuery = session.createQuery("SELECT u FROM User u", 
																User.class);
		
		List<User> usersList = usersQuery.getResultList();
		
		usersList.forEach(user -> System.out.println(user.getAuthorities()));
		return usersList;
	}

	@Override
	public User getUser(String username) {

		Session session = usersSessionFactory.getCurrentSession();
		
		User user = session.get(User.class, username);
		System.out.println(user.getAuthorities());
		
		return user;
	}

	@Override
	public void saveUser(User user) {

		Session session = usersSessionFactory.getCurrentSession();
		
		
		
		session.saveOrUpdate(user);
	}
	
	
	

}
