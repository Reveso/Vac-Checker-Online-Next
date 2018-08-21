package com.lukasrosz.vaccheckeronline;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication
@EnableAutoConfiguration(exclude=HibernateJpaAutoConfiguration.class)
public class VacCheckerOnlineApplication {

	public static void main(String[] args) {
		SpringApplication.run(VacCheckerOnlineApplication.class, args);
	}
}
