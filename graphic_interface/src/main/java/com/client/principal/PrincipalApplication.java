package com.client.principal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class PrincipalApplication {

	public static void main(String[] args) {
		SpringApplication.run(PrincipalApplication.class, args);
	}

}
