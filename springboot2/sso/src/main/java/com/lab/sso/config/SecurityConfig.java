package com.lab.sso.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
//@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
	//	@Bean
	@Override
	//	public SecurityFilterChain securityFilterChain( HttpSecurity http ) throws Exception
	protected void configure( HttpSecurity http ) throws Exception
	{
		http.authorizeRequests()
				.anyRequest().permitAll()
			.and()
				.csrf().disable();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}

}
