package com.lukasrosz.vaccheckeronline.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lukasrosz.vaccheckeronline.accounts.entity.UserDto;


@Repository
public class UserDAOImpl implements UserDAO {
	
	@Autowired
	private SessionFactory usersSessionFactory;

	@Override
	public List<UserDto> getUsersList() {
		Session session = usersSessionFactory.getCurrentSession();
		
		Query<UserDto> usersQuery = session.createQuery("SELECT u FROM UserDto u", 
																UserDto.class);
		
		List<UserDto> usersList = usersQuery.getResultList();
		
		usersList.forEach(user -> System.out.println(user.getAuthorities()));
		return usersList;
	}

	@Override
	public UserDto getUser(String username) {
		Session session = usersSessionFactory.getCurrentSession();
		
		UserDto user = session.get(UserDto.class, username);
		System.out.println(user.getAuthorities());
		
		return user;
	}

	@Override
	public void saveUser(UserDto user) {
		Session session = usersSessionFactory.getCurrentSession();
		
		session.saveOrUpdate(user);
	}
	
	
	

}
