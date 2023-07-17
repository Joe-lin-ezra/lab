package com.lab.base.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.lab.base.filter.MyAuthJWTTokenFilter;
import com.lab.base.security.MyAuthEntryPoint;
import com.lab.base.service.jwt.AuthJwtService;
import com.lab.base.service.jwt.decrypt.JwtService;
import com.lab.base.filter.MyFirstFilter;
import com.lab.base.service.TranLogService;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
	@Autowired
	protected AuthJwtService authJwtService;

	@Autowired
	protected JwtService jwtService;

	@Autowired
	protected TranLogService tranLogService;

	@Bean
	public MyAuthEntryPoint myAuthEntryPoint()
	{
		return new MyAuthEntryPoint();
	}

	@Bean
	@Override
	protected AuthenticationManager authenticationManager() throws Exception
	{
		return super.authenticationManager();
	}

//	protected MyAuthJWTTokenFilter authJWTTokenFilter()
//	{
//		return new MyAuthJWTTokenFilter( authJwtService );
//	}

	@Override
	protected void configure( HttpSecurity http ) throws Exception
	{
//		http.httpBasic().authenticationEntryPoint( myAuthEntryPoint() );
//		http.authorizeRequests().antMatchers( getPermitUrls() ).permitAll().anyRequest().authenticated();
//		http.addFilterBefore( firstFilter(), ChannelProcessingFilter.class );
//		http.addFilterAt( authJWTTokenFilter(), UsernamePasswordAuthenticationFilter.class );
//		http.sessionManagement().sessionCreationPolicy( SessionCreationPolicy.STATELESS );

		http.csrf().csrfTokenRepository( new CookieCsrfTokenRepository() );
	}

//	protected MyFirstFilter firstFilter()
//	{
//		return new MyFirstFilter( authJwtService, jwtService, tranLogService, getPermitUrls() );
//	}
//
//	protected String[] getPermitUrls()
//	{
//		return new String[]{ "/error/**", "/open/**", "/ixml/**" };
//	}

}
