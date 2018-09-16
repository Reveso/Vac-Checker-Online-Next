package com.lukasrosz.vaccheckeronline.steamapiintegration.playerbans;

import java.util.List;

public class PlayerBansWrapper {
	
	List<SteamPlayerBans> players;
	
	public PlayerBansWrapper() {
		
	}

	public List<SteamPlayerBans> getPlayers() {
		return players;
	}

	public void setPlayers(List<SteamPlayerBans> players) {
		this.players = players;
	}

	@Override
	public String toString() {
		return "PlayerBansWrapper [players=" + players + "]";
	}

}
