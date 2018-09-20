package com.lukasrosz.vaccheckeronline.dao;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lukasrosz.vaccheckeronline.suspects.entity.Suspect;


@Repository
public class SuspectDAOImpl implements SuspectDAO {

	@Autowired
	private SessionFactory mainDbSessionFactory;
	
	@Override
	public List<Suspect> getSuspects() {
		
		Session session = mainDbSessionFactory.getCurrentSession();
		
		Query<Suspect> suspectsQuery = session.createQuery("FROM Suspect ORDER BY additionDate DESC", 
																Suspect.class);
		
		List<Suspect> suspectsList = suspectsQuery.getResultList();
		
		return suspectsList;
	}

	@Override
	public boolean saveSuspect(Suspect suspect) {
		Session session = mainDbSessionFactory.getCurrentSession();
		
		Query<Suspect> suspectQuery = session.createQuery("FROM Suspect WHERE steamid=:suspectedSteamid OR id=:suspectedID", 
				Suspect.class);
		suspectQuery.setParameter("suspectedSteamid", suspect.getSteamid());
		suspectQuery.setParameter("suspectedID", suspect.getId());
	
		try {
			Suspect suspectFromDb = suspectQuery.getSingleResult();
			if(suspectFromDb.getId() == suspect.getId()) {
				Query<?> updateDescriptionQuery = session
						.createQuery("UPDATE Suspect SET description=:newDesc WHERE id=:susid");
				
				updateDescriptionQuery.setParameter("newDesc", suspect.getDescription());
				updateDescriptionQuery.setParameter("susid", suspectFromDb.getId());
				
				updateDescriptionQuery.executeUpdate();
				return true;
			}
		} catch (NoResultException e) {
			session.saveOrUpdate(suspect);
			return true;
		}
		
		return false;
	}
	
	@Override
	public void updateSuspect(Suspect suspect) {
		Session session = mainDbSessionFactory.getCurrentSession();
		
		session.update(suspect);
	}

	@Override
	public Suspect getSuspect(int id) {
		Session session = mainDbSessionFactory.getCurrentSession();
		Suspect suspect = session.get(Suspect.class, id);
		
		return suspect;
	}

	@Override
	public void deleteSuspect(int id) {
		Session session = mainDbSessionFactory.getCurrentSession();

		Query<?> query = session.createQuery("delete from Suspect where id=:suspectId");
		query.setParameter("suspectId", id);
		
		query.executeUpdate();
		
	}

}
