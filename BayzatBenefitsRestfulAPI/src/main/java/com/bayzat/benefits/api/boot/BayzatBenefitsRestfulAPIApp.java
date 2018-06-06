package com.bayzat.benefits.api.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages="com.bayzat.benefits.api")
public class BayzatBenefitsRestfulApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BayzatBenefitsRestfulApiApplication.class, args);
	}
}
