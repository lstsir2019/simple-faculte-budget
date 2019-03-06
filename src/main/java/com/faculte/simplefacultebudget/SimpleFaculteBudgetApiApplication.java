package com.faculte.simplefacultebudget;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("com.faculte.budgetapi.domain.rest")
@EnableDiscoveryClient
public class SimpleFaculteBudgetApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleFaculteBudgetApiApplication.class, args);
	}

}

