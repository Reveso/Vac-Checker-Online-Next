package com.lukasrosz.vaccheckeronline.steamapiintegration.playerbans;

import com.fasterxml.jackson.annotation.JsonAlias;

public class SteamPlayerBans {

	@JsonAlias("SteamId")
	private String steamId;
	@JsonAlias("CommunityBanned")
	private boolean communityBanned;
	@JsonAlias("VACBanned")
	private boolean vacBanned;
	@JsonAlias("NumberOfVACBans")
	private int numberOfVACBans;
	@JsonAlias("DaysSinceLastBan")
	private int daysSinceLastBan;
	@JsonAlias("NumberOfGameBans")
	private int numberOfGameBans;
	@JsonAlias("EconomyBan")
	private String economyBan;
    
    public SteamPlayerBans() {
    
    }

	public String getSteamId() {
		return steamId;
	}

	public void setSteamId(String steamId) {
		this.steamId = steamId;
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
		return daysSinceLastBan;
	}

	public void setDaysSinceLastBan(int daysSinceLastBan) {
		this.daysSinceLastBan = daysSinceLastBan;
	}

	public int getNumberOfGameBans() {
		return numberOfGameBans;
	}

	public void setNumberOfGameBans(int numberOfGameBans) {
		this.numberOfGameBans = numberOfGameBans;
	}

	public String getEconomyBan() {
		return economyBan;
	}

	public void setEconomyBan(String economyBan) {
		this.economyBan = economyBan;
	}

	@Override
	public String toString() {
		return "SteamPlayerBans [steamId=" + steamId + ", communityBanned=" + communityBanned + ", vacBanned="
				+ vacBanned + ", numberOfVACBans=" + numberOfVACBans + ", daysSinceLastBan=" + daysSinceLastBan
				+ ", numberOfGameBans=" + numberOfGameBans + ", economyBan=" + economyBan + "]";
	}
        
}
