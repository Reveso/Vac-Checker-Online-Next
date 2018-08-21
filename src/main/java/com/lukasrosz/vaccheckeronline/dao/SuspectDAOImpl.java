package com.lukasrosz.vaccheckeronline.dao;

import java.util.List;

import org.hibernate.NonUniqueObjectException;
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
		
//		Query<Suspect> suspectQuery = session.createQuery("FROM Suspect WHERE steamid=:suspectedID", 
//															Suspect.class);
//		suspectQuery.setParameter("suspectedID", suspect.getSteamid());
//		Suspect suspectFromDb = suspectQuery.getSingleResult();
//		
//		if(suspectFromDb != null) {
//			System.out.println("================================= >>>>>>>>> NOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOPE");
//			return false;
//		} 
// useless since exception will be thrown if not unique primaryKey - steamid
		
		try {
			session.save(suspect);
		} catch (NonUniqueObjectException e) {
			System.out.println("CATCHEEEEEEEEEEEEEEEED");
			return false;
		}
		
		return true;
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
