package com.lukasrosz.vaccheckeronline.steamapiintegration.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SteamPlayer {
	
	private String steamid;
	private String personaname;
	
	public SteamPlayer() {

	}
	
	public SteamPlayer(String steamid, String personaname) {
		this.steamid = steamid;
		this.personaname = personaname;
	}

	public String getSteamid() {
		return steamid;
	}
	
	public void setSteamid(String steamid) {
		this.steamid = steamid;
	}
	
	public String getPersonaname() {
		return personaname;
	}
	
	public void setPersonaname(String personaname) {
		this.personaname = personaname;
	}

	@Override
	public String toString() {
		return "SteamPlayer [steamid=" + steamid + ", personaname=" + personaname + "]";
	}

}
