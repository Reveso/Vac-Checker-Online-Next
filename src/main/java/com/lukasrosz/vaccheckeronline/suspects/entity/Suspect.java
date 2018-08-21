package com.lukasrosz.vaccheckeronline.suspects.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="suspects")
public class Suspect {

	@Id
	@Column(name="steamid")
	private String steamid;
	
	@Column(name="username")
	private String username;
	
	@Column(name="vac_status")
	private String vacStatus;
	
	@Column(name="description")
	private String description;
	
	@Column(name="addition_date", nullable = false)
//	@Temporal(value = TemporalType.TIMESTAMP)
//	@Generated(value = GenerationTime.ALWAYS)
	private Date additionDate;
	
	@Column(name="number_of_bans_when_added")
	private int numberOfBansWhenAdded;
	
	public Suspect() {
	
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

	public int getNumberOfBansWhenAdded() {
		return numberOfBansWhenAdded;
	}

	public void setNumberOfBansWhenAdded(int numberOfBansWhenAdded) {
		this.numberOfBansWhenAdded = numberOfBansWhenAdded;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((steamid == null) ? 0 : steamid.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Suspect))
			return false;
		Suspect other = (Suspect) obj;
		if (steamid == null) {
			if (other.steamid != null)
				return false;
		} else if (!steamid.equals(other.steamid))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Suspect [steamid=" + steamid + ", username=" + username + ", vacStatus=" + vacStatus + ", description="
				+ description + ", additionDate=" + additionDate + ", numberOfBansWhenAdded=" + numberOfBansWhenAdded
				+ "]";
	}
	
}
