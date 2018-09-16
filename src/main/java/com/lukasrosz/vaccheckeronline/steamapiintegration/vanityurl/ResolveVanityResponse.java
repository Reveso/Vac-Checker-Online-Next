package com.lukasrosz.vaccheckeronline.steamapiintegration.vanityurl;
public class ResolveVanityResponse {

	private String steamid;
	private int success;
	
	public ResolveVanityResponse() {

	}
	
	public ResolveVanityResponse(String steamid, int success) {
		this.steamid = steamid;
		this.success = success;
	}
	
	public String getSteamid() {
		return steamid;
	}
	
	public void setSteamid(String steamid) {
		this.steamid = steamid;
	}
	
	public int getSuccess() {
		return success;
	}
	
	public void setSuccess(int success) {
		this.success = success;
	}
	
	@Override
	public String toString() {
		return "ResolveVanityResponse [steamid=" + steamid + ", success=" + success + "]";
	}
	
}
