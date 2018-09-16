package com.lukasrosz.vaccheckeronline.dao;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lukasrosz.vaccheckeronline.suspects.entity.SuspectDto;


@Repository
public class SuspectDAOImpl implements SuspectDAO {

	@Autowired
	private SessionFactory mainDbSessionFactory;
	
	@Override
	public List<SuspectDto> getSuspects() {
		
		Session session = mainDbSessionFactory.getCurrentSession();
		
		Query<SuspectDto> suspectsQuery = session.createQuery("FROM SuspectDto ORDER BY additionDate DESC", 
																SuspectDto.class);
		
		List<SuspectDto> suspectsList = suspectsQuery.getResultList();
		
		return suspectsList;
	}

	@Override
	public boolean saveSuspect(SuspectDto suspect) {
		Session session = mainDbSessionFactory.getCurrentSession();
		
		Query<SuspectDto> suspectQuery = session.createQuery("FROM SuspectDto WHERE steamid=:suspectedID", 
				SuspectDto.class);
		suspectQuery.setParameter("suspectedID", suspect.getSteamid());
		
		try {
			SuspectDto suspectFromDb = suspectQuery.getSingleResult();
			if(suspectFromDb.getId() == suspect.getId()) {
				Query<?> updateDescriptionQuery = session
						.createQuery("UPDATE SuspectDto SET description=:newDesc WHERE id=:susid");
				
				updateDescriptionQuery.setParameter("newDesc", suspect.getDescription());
				updateDescriptionQuery.setParameter("susid", suspectFromDb.getId());
				
				updateDescriptionQuery.executeUpdate();
				return true;
			}
		} catch (NoResultException e) {
			System.out.println("In exception ==========================================================================");
			session.saveOrUpdate(suspect);
			return true;
		}
		
		return false;
	}
	
	@Override
	public void updateSuspect(SuspectDto suspect) {
		Session session = mainDbSessionFactory.getCurrentSession();
		
		session.update(suspect);
	}

	@Override
	public SuspectDto getSuspect(int id) {
		Session session = mainDbSessionFactory.getCurrentSession();
		SuspectDto suspect = session.get(SuspectDto.class, id);
		
		return suspect;
	}

	@Override
	public void deleteSuspect(int id) {
		Session session = mainDbSessionFactory.getCurrentSession();

		Query<?> query = session.createQuery("delete from SuspectDto where id=:suspectId");
		query.setParameter("suspectId", id);
		
		query.executeUpdate();
		
	}

}
