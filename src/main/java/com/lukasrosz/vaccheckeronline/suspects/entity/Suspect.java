package com.lukasrosz.vaccheckeronline.suspects.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.springframework.web.client.RestTemplate;

import com.lukasrosz.vaccheckeronline.steamapiintegration.playerbans.PlayerBansWrapper;
import com.lukasrosz.vaccheckeronline.steamapiintegration.playersummaries.PlayerSummariesWrapper;
import com.lukasrosz.vaccheckeronline.steamapiintegration.urlmaker.SteamApiUrlMaker;
import com.lukasrosz.vaccheckeronline.steamapiintegration.vanityurl.ResolveVanityWrapper;

@Entity
@Table(name="suspects")
public class Suspect {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@NotNull
	@Column(name="steamid")
	private String steamid;
	
	@Column(name="username")
	private String username;
	
	@Column(name="vac_status", nullable = false)
	private String vacStatus = "loading";
	
	@Column(name="description")
	private String description;
	
	@Column(name="addition_date", nullable = false)
	@Temporal(value = TemporalType.TIMESTAMP)
	@Generated(value = GenerationTime.ALWAYS)
	private Date additionDate;
	
	public boolean isSteamAccount(RestTemplate restTemplate, 
			SteamApiUrlMaker steamApiUrlMaker) {
		
		resolveVanity(restTemplate, steamApiUrlMaker);
		
		PlayerSummariesWrapper summariesResponse = restTemplate
				.getForObject(steamApiUrlMaker
						.getPlayerSummariesUrl(new String[] {getSteamid()}), 
						PlayerSummariesWrapper.class);
		
		if(summariesResponse.getResponse().getPlayers().size() > 0) {
			setUsername(summariesResponse.getResponse()
					.getPlayers().get(0).getPersonaname());
			setSteamid(summariesResponse.getResponse().
					getPlayers().get(0).getSteamid());
			return true;
		}
		return false;
	}
	
	private void resolveVanity(RestTemplate restTemplate,
			SteamApiUrlMaker steamApiUrlMaker) {
		ResolveVanityWrapper vanityResponse = restTemplate
				.getForObject(steamApiUrlMaker.resolveVanityUrl(getSteamid()), 
						ResolveVanityWrapper.class);
		
		if (vanityResponse.getResponse().getSuccess() == 1) {
			setSteamid(vanityResponse.getResponse().getSteamid());
		} 
	}
	
	public void updateVACStatus(RestTemplate restTemplate,
			SteamApiUrlMaker steamApiUrlMaker) {
		
		PlayerBansWrapper playerBansResponse = 
				restTemplate
				.getForObject(steamApiUrlMaker.getPlayerBansUrl
						(new String[] {getSteamid()}), PlayerBansWrapper.class);
		
		if(playerBansResponse.getPlayers().get(0).isVacBanned()) {
			setVacStatus("Banned");
		} else {
			setVacStatus("Clear");
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSteamid() {
		return steamid;
	}

	public void setSteamid(String steamid) {
		this.steamid = steamid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getVacStatus() {
		return vacStatus;
	}

	public void setVacStatus(String vacStatus) {
		this.vacStatus = vacStatus;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getAdditionDate() {
		return additionDate;
	}

	public void setAdditionDate(Date additionDate) {
		this.additionDate = additionDate;
	}

	@Override
	public String toString() {
		return "SuspectDto [id=" + id + ", steamid=" + steamid + ", username=" + username + ", vacStatus=" + vacStatus
				+ ", description=" + description + ", additionDate=" + additionDate + "]";
	}

}
