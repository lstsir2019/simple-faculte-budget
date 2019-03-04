package com.faculte.simplefacultebudget;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;

@SpringBootApplication
@EnableFeignClients("com.faculte.budgetapi.domain.rest")
public class SimpleFaculteBudgetApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleFaculteBudgetApiApplication.class, args);
	}

}

