package com.patitas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "application", "adapter" , "com.patitas.duenio")
@EntityScan(basePackages = "domain.model")
@EnableJpaRepositories(basePackages = "adapter")
public class MsDuenioApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsDuenioApplication.class, args);
	}

}
