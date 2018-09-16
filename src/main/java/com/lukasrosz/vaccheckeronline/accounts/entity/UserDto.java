package com.lukasrosz.vaccheckeronline.accounts.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="users")
public class UserDto {

	@Id
	@NotNull
	@Column(name="username")
	private String username;
	
	@NotNull
	@Column(name="password")
	private String password;

	@NotNull
	@Column(name="enabled")
	private boolean enabled;
	
	@OneToMany(mappedBy="username", fetch=FetchType.EAGER,
			cascade=CascadeType.ALL)
	private List<AuthorityDto> authorities = new ArrayList<>();
			
	public UserDto() {

	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public List<AuthorityDto> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(List<AuthorityDto> authorities) {
		this.authorities = authorities;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", enabled=" + enabled +"]";
	}
	
}
