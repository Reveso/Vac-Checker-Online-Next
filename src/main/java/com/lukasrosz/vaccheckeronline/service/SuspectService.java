package com.lukasrosz.vaccheckeronline.service;

import java.util.List;

import com.lukasrosz.vaccheckeronline.suspects.entity.Suspect;


public interface SuspectService {

	public List<Suspect> getSuspects();
	
	public boolean saveSuspect(Suspect suspect);
	
	public Suspect getSuspect(int id);

	public void deleteSuspect(int id);

	public void updateSuspect(Suspect suspect);
}
