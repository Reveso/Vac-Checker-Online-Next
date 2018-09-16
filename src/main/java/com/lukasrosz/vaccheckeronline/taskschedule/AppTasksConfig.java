package com.lukasrosz.vaccheckeronline.taskschedule;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;

import com.lukasrosz.vaccheckeronline.service.SuspectService;
import com.lukasrosz.vaccheckeronline.steamapiintegration.urlmaker.SteamApiUrlMaker;
import com.lukasrosz.vaccheckeronline.suspects.entity.SuspectDto;


@Configuration
@EnableScheduling
public class AppTasksConfig {
	
	@Autowired
	private SuspectService suspectService;
	@Autowired
	private SteamApiUrlMaker steamApiUrlMaker;
	
	//28800000 - 8 hours
	@Scheduled(fixedRate=28800000)
	public void doSomething() {
		System.out.println("========================================================>>>>> Beginning scheduled task<<<<====================================================");
		List<SuspectDto> suspects = suspectService.getSuspects();
		RestTemplate restTemplate = new RestTemplate();
		
		suspects.parallelStream().forEach(suspect -> {
			if(suspect.getAdditionDate() == null) {
				suspect.setAdditionDate(new Date());
			}
			suspect.updateVACStatus(restTemplate, steamApiUrlMaker);
			suspectService.updateSuspect(suspect);
		});		
		
		System.out.println("========================================================>>>>> Ending scheduled task <<<<====================================================");
	}
}
