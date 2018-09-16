package com.lukasrosz.vaccheckeronline.service;

import java.util.List;

import com.lukasrosz.vaccheckeronline.suspects.entity.SuspectDto;


public interface SuspectService {

	public List<SuspectDto> getSuspects();
	
	public boolean saveSuspect(SuspectDto suspect);
	
	public SuspectDto getSuspect(int id);

	public void deleteSuspect(int id);

	public void updateSuspect(SuspectDto suspect);
}
