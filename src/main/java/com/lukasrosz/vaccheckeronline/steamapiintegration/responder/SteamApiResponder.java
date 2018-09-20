package com.lukasrosz.vaccheckeronline.steamapiintegration.responder;

import java.util.List;
import java.util.Map;

import com.lukasrosz.vaccheckeronline.steamapiintegration.playerbans.PlayerBansWrapper;
import com.lukasrosz.vaccheckeronline.steamapiintegration.playerbans.SteamPlayerBans;
import com.lukasrosz.vaccheckeronline.steamapiintegration.playersummaries.PlayerSummariesWrapper;
import com.lukasrosz.vaccheckeronline.steamapiintegration.vanityurl.ResolveVanityWrapper;
import com.lukasrosz.vaccheckeronline.suspects.entity.Suspect;

public interface SteamApiResponder {
	
	public ResolveVanityWrapper resolveVanity(String steamId);
	
	public PlayerBansWrapper getPlayerBans(String[] steamIds);
	
	public PlayerSummariesWrapper getPlayerSummaries(String[] steamIds);

	public String resolveVanityUrl(String steamId);
	
	public String getPlayerSummariesUrl(String[] steamIds);

	public String getPlayerBansUrl(String[] steamIds);

	public Map<String, SteamPlayerBans> bansWrapperToMap(PlayerBansWrapper playerBansWrapper);

	public String[] suspectLstToIdsArray(List<Suspect> suspects);
}
