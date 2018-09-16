package com.lukasrosz.vaccheckeronline.steamapiintegration.playersummaries;

public class PlayerSummariesWrapper {
	
	private PlayerSummariesResponse response;

	public PlayerSummariesResponse getResponse() {
		return response;
	}

	public void setResponse(PlayerSummariesResponse response) {
		this.response = response;
	}

	@Override
	public String toString() {
		return "PlayerSummariesWrapper [response=" + response + "]";
	}
	
	

}
