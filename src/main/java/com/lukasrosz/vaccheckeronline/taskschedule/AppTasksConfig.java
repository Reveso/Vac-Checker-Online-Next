package com.lukasrosz.vaccheckeronline.taskschedule;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.lukasrosz.vaccheckeronline.service.SuspectService;
import com.lukasrosz.vaccheckeronline.steamapiintegration.playerbans.PlayerBansWrapper;
import com.lukasrosz.vaccheckeronline.steamapiintegration.playerbans.SteamPlayerBans;
import com.lukasrosz.vaccheckeronline.steamapiintegration.responder.SteamApiResponder;
import com.lukasrosz.vaccheckeronline.suspects.entity.Suspect;


@Configuration
@EnableScheduling
public class AppTasksConfig {
	
	@Autowired
	private SuspectService suspectService;
	@Autowired
	private SteamApiResponder steamApiResponder;
	
	//28800000 - 8 hours
	//14400000 - 4 hours
	@Scheduled(fixedRate=14400000)
	public void updateVacStatuses() {
		System.out.println("========================================================>>>>> Beginning scheduled task<<<<====================================================");
		List<Suspect> suspects = suspectService.getSuspects();
		
		
		PlayerBansWrapper playerBansWrapper = steamApiResponder.getPlayerBans(steamApiResponder.suspectLstToIdsArray(suspects));
		Map<String, SteamPlayerBans> playersBans = steamApiResponder
				.bansWrapperToMap(playerBansWrapper);
		
		suspects.parallelStream().forEach(suspect -> {
			if(suspect.getAdditionDate() == null) {
				suspect.setAdditionDate(new Date());
			}
			
			if(playersBans.get(suspect.getSteamid()).isVacBanned()) {
				suspect.setVacStatus("Banned");
				System.out.println(suspect.getSteamid() + " Banned");
			} else {
				suspect.setVacStatus("Clear");
				System.out.println(suspect.getSteamid() + " Clear");
			}
			
			suspectService.updateSuspect(suspect);
		});		
		
		System.out.println("========================================================>>>>> Ending scheduled task <<<<====================================================");
	}
	
}
