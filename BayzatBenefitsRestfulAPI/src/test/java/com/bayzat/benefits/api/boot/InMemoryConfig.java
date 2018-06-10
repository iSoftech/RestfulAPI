package com.bayzat.benefits.api.boot;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.web.context.WebApplicationContext;

@Configuration
@Profile("test")
public class InMemoryConfig {
	private final String TEST_DATA = "classpath:data-test.sql";
	
	@Autowired
	private DataSource datasource;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@PostConstruct
	public void loadIfInMemory() throws Exception {
		Resource resource = webApplicationContext.getResource(TEST_DATA);
		ScriptUtils.executeSqlScript(datasource.getConnection(), resource);
	}
}
