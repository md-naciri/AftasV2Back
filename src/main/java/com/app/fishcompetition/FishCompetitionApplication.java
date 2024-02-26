package com.app.fishcompetition;

import com.app.fishcompetition.config.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true)
public class FishCompetitionApplication {

	public static void main(String[] args) {
		SpringApplication.run(FishCompetitionApplication.class, args);
	}

}
