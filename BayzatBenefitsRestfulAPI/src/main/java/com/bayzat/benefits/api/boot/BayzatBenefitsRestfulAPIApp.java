package com.bayzat.benefits.api.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Mohamed Yusuff
 *
 */
@SpringBootApplication(scanBasePackages="com.bayzat.benefits.api")
public class BayzatBenefitsRestfulAPIApp {

	public static void main(String[] args) {
		SpringApplication.run(BayzatBenefitsRestfulAPIApp.class, args);
	}
}
