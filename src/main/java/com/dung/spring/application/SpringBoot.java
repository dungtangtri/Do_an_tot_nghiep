package com.dung.spring.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;

@SpringBootApplication

public class SpringBoot {

	public static void main(String[] args) {
		SpringApplication.run(SpringBoot.class, args);
	}

}
