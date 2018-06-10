package com.bayzat.benefits.api.boot;

import org.apache.catalina.security.SecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * Bayzat Benefits Restful API Spring Boot Application
 * 
 * @author Mohamed Yusuff
 */
@SpringBootApplication(scanBasePackages="com.bayzat.benefits.api")
@Import({SecurityConfig.class})
public class BayzatBenefitsRestfulAPIApp {

	public static void main(String[] args) {
		SpringApplication.run(BayzatBenefitsRestfulAPIApp.class, args);
	}
}
