package com.lab.sso.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

//import com.lab.springboot2.filter.MyAuthJWTTokenFilter;
//import com.lab.springboot2.security.MyAuthEntryPoint;
//import com.lab.springboot2.service.jwt.AuthJwtService;
//import com.lab.springboot2.service.jwt.decrypt.JwtService;
//import com.lab.springboot2.filter.MyFirstFilter;
//import com.lab.springboot2.service.TranLogService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity
//@EnableWebFluxSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests().anyRequest().permitAll().and()
                .httpBasic().and().csrf().disable();
//        http.csrf().csrfTokenRepository(new CookieCsrfTokenRepository());

        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //	@Autowired
    //	protected AuthJwtService authJwtService;
    //
    //	@Autowired
    //	protected JwtService jwtService;
    //
    //	@Autowired
    //	protected TranLogService tranLogService;
    //
    //	@Bean
    //	public MyAuthEntryPoint myAuthEntryPoint()
    //	{
    //		return new MyAuthEntryPoint();
    //	}

    //	@Bean
    //	@Override
    //	protected AuthenticationManager authenticationManager() throws Exception
    //	{
    //		return super.authenticationManager();
    //	}

    //	protected MyAuthJWTTokenFilter authJWTTokenFilter()
    //	{
    //		return new MyAuthJWTTokenFilter( authJwtService );
    //	}

    //	@Override
    //	protected void configure( HttpSecurity http ) throws Exception
    //	{
    ////		http.httpBasic().authenticationEntryPoint( myAuthEntryPoint() );
    ////		http.authorizeRequests().antMatchers( getPermitUrls() ).permitAll().anyRequest().authenticated();
    ////		http.addFilterBefore( firstFilter(), ChannelProcessingFilter.class );
    ////		http.addFilterAt( authJWTTokenFilter(), UsernamePasswordAuthenticationFilter.class );
    ////		http.sessionManagement().sessionCreationPolicy( SessionCreationPolicy.STATELESS );
    //
    //		http.csrf().csrfTokenRepository( new CookieCsrfTokenRepository() );
    //	}
}
