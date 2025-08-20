package com.resigtry_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ResigtryServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ResigtryServerApplication.class, args);
	}
}
