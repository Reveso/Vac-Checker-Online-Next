package com.lukasrosz.vaccheckeronline.taskschedule;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.lukasrosz.vaccheckeronline.service.SuspectService;
import com.lukasrosz.vaccheckeronline.suspects.entity.Suspect;


@Configuration
@EnableScheduling
public class AppTasksConfig {
	
	@Autowired
	private SuspectService suspectService;
	
	//28800000 - 8 hours
	@Scheduled(fixedRate=28800000)
	public void doSomething() {
		System.out.println("========================================================>>>>> Beginning scheduled task<<<<====================================================");
		List<Suspect> suspects = suspectService.getSuspects();
		
		suspects.parallelStream().forEach(suspect -> {
			if(suspect.getAdditionDate() == null) {
				suspect.setAdditionDate(new Date());
			}
			suspectService.updateSuspect(suspect);
		});
		
		
		System.out.println("========================================================>>>>> Ending scheduled task <<<<====================================================");
	}
}
