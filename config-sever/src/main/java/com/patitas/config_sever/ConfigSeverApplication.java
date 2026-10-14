package com.patitas.config_sever;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class ConfigSeverApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigSeverApplication.class, args);
	}

}
