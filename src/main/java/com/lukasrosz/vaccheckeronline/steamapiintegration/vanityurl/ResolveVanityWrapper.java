package com.lukasrosz.vaccheckeronline.steamapiintegration.vanityurl;

public class ResolveVanityWrapper {
	
	private ResolveVanityResponse response;
	
	public ResolveVanityWrapper() {

	}

	public ResolveVanityWrapper(ResolveVanityResponse response) {
		this.response = response;
	}

	public ResolveVanityResponse getResponse() {
		return response;
	}

	public void setResponse(ResolveVanityResponse response) {
		this.response = response;
	}

	@Override
	public String toString() {
		return "ResponseResponse [response=" + response + "]";
	}
	
}
