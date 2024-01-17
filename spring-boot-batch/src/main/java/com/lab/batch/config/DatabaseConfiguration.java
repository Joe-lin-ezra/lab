package com.lab.batch.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.support.JdbcTransactionManager;

@Configuration
public class DatabaseConfiguration {
	@Bean
	public JdbcTransactionManager transactionManager(DataSource dataSource) {
	    return new JdbcTransactionManager(dataSource);
	}
}
