package com.vetSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VetSystemApplication {

	public static void main(String[] args) {
		System.out.println("Hola Mundo");
		SpringApplication.run(VetSystemApplication.class, args);
	}

}
