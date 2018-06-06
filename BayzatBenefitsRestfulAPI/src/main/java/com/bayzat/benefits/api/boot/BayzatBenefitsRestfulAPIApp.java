package com.bayzat.benefits.api.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author Mohamed Yusuff
 *
 */
@SpringBootApplication(scanBasePackages="com.bayzat.benefits.api")
@EntityScan("com.bayzat.benefits.api.model")
@EnableJpaRepositories("com.bayzat.benefits.api.repo")
public class BayzatBenefitsRestfulAPIApp {

	public static void main(String[] args) {
		SpringApplication.run(BayzatBenefitsRestfulAPIApp.class, args);
	}
}
