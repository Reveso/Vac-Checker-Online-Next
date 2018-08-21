package com.lukasrosz.vaccheckeronline.steamapiintegration.entity;

import java.util.List;

public class GetPlayerSummariesResponse {
	
	private List<SteamPlayer> players;

	public GetPlayerSummariesResponse() {
		
	}
	
	public GetPlayerSummariesResponse(List<SteamPlayer> players) {
		this.players = players;
	}

	public List<SteamPlayer> getPlayers() {
		return players;
	}

	public void setPlayers(List<SteamPlayer> players) {
		this.players = players;
	}

	@Override
	public String toString() {
		return "GetPlayerSummariesResponse [players=" + players + "]";
	}

}
