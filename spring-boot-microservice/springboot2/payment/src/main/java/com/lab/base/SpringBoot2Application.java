package com.lab.base;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication
@EnableWebFlux
public class SpringBoot2Application
{

	public static void main(String[] args) {
		SpringApplication.run( SpringBoot2Application.class, args);
	}

}
