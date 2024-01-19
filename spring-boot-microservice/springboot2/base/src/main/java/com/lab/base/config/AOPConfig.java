package com.lab.base.config;

import com.lab.base.aspect.DAOAspect;
import com.lab.base.util.LoggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class AOPConfig
{
	private LoggerService loggerService;

	@Autowired
	public AOPConfig( LoggerService loggerService )
	{
		this.loggerService = loggerService;
	}

	@Bean
	public DAOAspect sqlPerformanceAspect() {
		return new DAOAspect(loggerService);
	}
}
