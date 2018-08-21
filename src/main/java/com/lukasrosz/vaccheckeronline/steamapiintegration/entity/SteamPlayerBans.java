package com.lukasrosz.vaccheckeronline.steamapiintegration.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SteamPlayerBans {

	private String SteamId;
    private boolean communityBanned;
    private boolean vacBanned;
    private int numberOfVACBans;
    private int DaysSinceLastBan;
    private int NumberOfGameBans;
    
    public SteamPlayerBans() {
    
    }
	public String getSteamId() {
		return SteamId;
	}
	public void setSteamId(String steamId) {
		SteamId = steamId;
	}
	public boolean isCommunityBanned() {
		return communityBanned;
	}
	public void setCommunityBanned(boolean communityBanned) {
		this.communityBanned = communityBanned;
	}
	public boolean isVacBanned() {
		return vacBanned;
	}
	public void setVacBanned(boolean vacBanned) {
		this.vacBanned = vacBanned;
	}
	public int getNumberOfVACBans() {
		return numberOfVACBans;
	}
	public void setNumberOfVACBans(int numberOfVACBans) {
		this.numberOfVACBans = numberOfVACBans;
	}
	public int getDaysSinceLastBan() {
		return DaysSinceLastBan;
	}
	public void setDaysSinceLastBan(int daysSinceLastBan) {
		DaysSinceLastBan = daysSinceLastBan;
	}
	public int getNumberOfGameBans() {
		return NumberOfGameBans;
	}
	public void setNumberOfGameBans(int numberOfGameBans) {
		NumberOfGameBans = numberOfGameBans;
	}
	
	@Override
	public String toString() {
		return "SteamPlayerBans [SteamId=" + SteamId + ", communityBanned=" + communityBanned + ", vacBanned="
				+ vacBanned + ", numberOfVACBans=" + numberOfVACBans + ", DaysSinceLastBan=" + DaysSinceLastBan
				+ ", NumberOfGameBans=" + NumberOfGameBans + "]";
	}
    
}
