package com.lab.base.config;

import com.lab.base.aspect.DAOAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class AOPConfig
{
	@Bean
	public DAOAspect sqlPerformanceAspect() {
		return new DAOAspect();
	}
}
