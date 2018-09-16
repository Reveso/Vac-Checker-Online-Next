package com.lukasrosz.vaccheckeronline.steamapiintegration.urlmaker;

public interface SteamApiUrlMaker {
	
	public String resolveVanityUrl(String steamId);
	
	public String getPlayerSummariesUrl(String[] steamIds);

	public String getPlayerBansUrl(String[] steamIds);

}
