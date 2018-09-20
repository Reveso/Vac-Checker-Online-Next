package com.lukasrosz.vaccheckeronline.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lukasrosz.vaccheckeronline.accounts.entity.UserDto;
import com.lukasrosz.vaccheckeronline.dao.SuspectDAO;
import com.lukasrosz.vaccheckeronline.dao.UserDAO;
import com.lukasrosz.vaccheckeronline.suspects.entity.Suspect;

@Service
public class MainDatabaseServiceImpl implements UserService, SuspectService {

	@Autowired
	private SuspectDAO suspectDAO;
	@Autowired
	private UserDAO userDAO;
	
	
	@Override
	@Transactional(value="mainDBTransactionManager")
	public List<Suspect> getSuspects() {
		return suspectDAO.getSuspects();
	}

	@Override
	@Transactional(value="mainDBTransactionManager")
	public boolean saveSuspect(Suspect suspect) {
		return suspectDAO.saveSuspect(suspect);
	}

	@Override
	@Transactional(value="mainDBTransactionManager")
	public Suspect getSuspect(int id) {
		return suspectDAO.getSuspect(id);
	}

	@Override
	@Transactional(value="mainDBTransactionManager")
	public void updateSuspect(Suspect suspect) {
		suspectDAO.updateSuspect(suspect);
	}
	
	@Override
	@Transactional(value="mainDBTransactionManager")
	public void deleteSuspect(int id) {
		suspectDAO.deleteSuspect(id);
	}	
	
	@Override
	@Transactional(value="mainDBTransactionManager")
	public List<UserDto> getUsersList() {
		return userDAO.getUsersList();
	}

	@Override
	@Transactional(value="mainDBTransactionManager")
	public UserDto getUser(String username) {
		return userDAO.getUser(username);
	}

	@Override
	@Transactional(value="mainDBTransactionManager")
	public void saveUser(UserDto user) {
		userDAO.saveUser(user);
	}


}
