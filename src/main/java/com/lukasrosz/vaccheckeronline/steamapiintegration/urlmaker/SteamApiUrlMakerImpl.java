package com.lukasrosz.vaccheckeronline.steamapiintegration.urlmaker;

public final class SteamApiUrlMakerImpl implements SteamApiUrlMaker {
	
	private final String apiKey;
	
	public SteamApiUrlMakerImpl(String apiKey) {
		this.apiKey = apiKey;
	}
	
	public String resolveVanityUrl(String steamId) {
		return "https://api.steampowered.com/ISteamUser/ResolveVanityURL/v1/?key=" + apiKey + "&vanityurl=" + steamId;
	}
	
	public String getPlayerSummariesUrl(String[] steamIds) {
		StringBuilder url =  new StringBuilder("https://api.steampowered.com/ISteamUser/GetPlayerSummaries/v2/?key=")
				.append(apiKey).append("&steamids=");
		
		for(String id : steamIds) {
			url.append(id).append(",");
		}
		return url.toString();
	}

	public String getPlayerBansUrl(String[] steamIds) {
		StringBuilder url = new StringBuilder("https://api.steampowered.com/ISteamUser/GetPlayerBans/v1/?key=")
				.append(apiKey).append("&steamids=");
		
		for(String id : steamIds) {
			url.append(id).append(",");
		}
		
		return url.toString();
	}
}
