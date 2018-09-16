package com.lukasrosz.vaccheckeronline.dao;

import java.util.List;

import com.lukasrosz.vaccheckeronline.suspects.entity.SuspectDto;


public interface SuspectDAO {

	public List<SuspectDto> getSuspects();
	
	public boolean saveSuspect(SuspectDto suspect);
		
	public SuspectDto getSuspect(int id);
	
	public void deleteSuspect(int id);

	public void updateSuspect(SuspectDto suspect);

}
