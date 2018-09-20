package com.lukasrosz.vaccheckeronline.steamapiintegration.responder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.client.RestTemplate;

import com.lukasrosz.vaccheckeronline.steamapiintegration.playerbans.PlayerBansWrapper;
import com.lukasrosz.vaccheckeronline.steamapiintegration.playerbans.SteamPlayerBans;
import com.lukasrosz.vaccheckeronline.steamapiintegration.playersummaries.PlayerSummariesWrapper;
import com.lukasrosz.vaccheckeronline.steamapiintegration.vanityurl.ResolveVanityWrapper;
import com.lukasrosz.vaccheckeronline.suspects.entity.Suspect;

public final class SteamApiResponderImpl implements SteamApiResponder {
	
	private final String apiKey;
	private final RestTemplate restTemplate;
	
	public SteamApiResponderImpl(String apiKey) {
		this.apiKey = apiKey;
		this.restTemplate = new RestTemplate();
	}
	
	@Override
	public Map<String, SteamPlayerBans> bansWrapperToMap(PlayerBansWrapper playerBansWrapper) {
		Map<String, SteamPlayerBans> bansMap = new HashMap<>();
		playerBansWrapper.getPlayers().forEach(playerBans -> bansMap.put(playerBans.getSteamId(), playerBans));
		return bansMap;
	}
	
	@Override
	public ResolveVanityWrapper resolveVanity(String steamId) {
		ResolveVanityWrapper vanityResponse = restTemplate
				.getForObject(resolveVanityUrl(steamId), 
						ResolveVanityWrapper.class);
		return vanityResponse;
	}
	
	@Override
	public PlayerBansWrapper getPlayerBans(String[] steamIds) {
		PlayerBansWrapper playerBansResponse = restTemplate
				.getForObject(getPlayerBansUrl(steamIds), PlayerBansWrapper.class);
		return playerBansResponse;
	}
	
	@Override
	public PlayerSummariesWrapper getPlayerSummaries(String[] steamIds) {
		PlayerSummariesWrapper playerSummariesResponse = restTemplate
				.getForObject(getPlayerSummariesUrl(steamIds), PlayerSummariesWrapper.class);
		return playerSummariesResponse;
	}
	
	@Override
	public String resolveVanityUrl(String steamId) {
		return "https://api.steampowered.com/ISteamUser/ResolveVanityURL/v1/?key=" + apiKey + "&vanityurl=" + steamId;
	}
	
	@Override
	public String getPlayerSummariesUrl(String[] steamIds) {
		StringBuilder url =  new StringBuilder("https://api.steampowered.com/ISteamUser/GetPlayerSummaries/v2/?key=")
				.append(apiKey).append("&steamids=");
		
		for(String id : steamIds) {
			url.append(id).append(",");
		}
		return url.toString();
	}

	@Override
	public String getPlayerBansUrl(String[] steamIds) {
		StringBuilder url = new StringBuilder("https://api.steampowered.com/ISteamUser/GetPlayerBans/v1/?key=")
				.append(apiKey).append("&steamids=");
		
		for(String id : steamIds) {
			url.append(id).append(",");
		}
		
		return url.toString();
	}
	
	@Override
	public String[] suspectLstToIdsArray(List<Suspect> suspects) {
		String[] steamIds = new String[suspects.size()];
		for(int i=0; i < suspects.size(); i++) {
			steamIds[i] = suspects.get(i).getSteamid();
		}
		return steamIds;
	}
}
