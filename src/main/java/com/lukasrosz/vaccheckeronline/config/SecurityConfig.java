package com.lukasrosz.vaccheckeronline.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource mainDataSource;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.jdbcAuthentication().dataSource(mainDataSource);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
			.antMatchers("/suspects/showFormForAdd").hasRole("REGISTERED")
			.antMatchers("/suspects/showFormForUpdate").hasAnyRole("MODERATOR", "ADMIN")
			.antMatchers("/suspects/saveSuspect").hasRole("REGISTERED")
			.antMatchers("/suspects/delete").hasRole("ADMIN")
			.antMatchers("/adminPanel/**").hasRole("ADMIN")
			.and()
			.formLogin()
				.loginPage("/users/login")
				.loginProcessingUrl("/authenticateUser")
				.defaultSuccessUrl("/")
				.permitAll()
			.and()
			.logout().permitAll()
			.logoutSuccessUrl("/")
			.and()
			.exceptionHandling().accessDeniedPage("/users/access-denied")
			.and()
			.rememberMe().key("uniqueAndSecret"); //TODO
	}
	
	@Bean
	public UserDetailsManager userDetailsManager() {
		
		JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager();
		jdbcUserDetailsManager.setDataSource(mainDataSource);
		
		return jdbcUserDetailsManager;
	}
	


}
