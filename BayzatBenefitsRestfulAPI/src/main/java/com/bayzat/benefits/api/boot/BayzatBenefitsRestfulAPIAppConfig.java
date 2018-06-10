package com.bayzat.benefits.api.boot;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Bayzat Benefits Restful API Spring Boot Application Configuration
 * 
 * @author Mohamed Yusuff
 */
@Configuration
@EntityScan("com.bayzat.benefits.api.model")
@EnableJpaRepositories("com.bayzat.benefits.api.repo")
@EnableTransactionManagement
public class BayzatBenefitsRestfulAPIAppConfig {

}
