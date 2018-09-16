package com.lukasrosz.vaccheckeronline.steamapiintegration.playersummaries;

import java.util.List;

public class PlayerSummariesResponse {
	
	private List<SteamPlayerSummary> players;

	public PlayerSummariesResponse() {
		
	}
	
	public PlayerSummariesResponse(List<SteamPlayerSummary> players) {
		this.players = players;
	}

	public List<SteamPlayerSummary> getPlayers() {
		return players;
	}

	public void setPlayers(List<SteamPlayerSummary> players) {
		this.players = players;
	}

	@Override
	public String toString() {
		return "PlayerSummariesResponse [players=" + players + "]";
	}

}
