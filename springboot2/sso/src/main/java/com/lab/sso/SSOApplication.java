package com.lab.sso;

import com.lab.base.BaseApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;
//import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication
//@EnableWebFlux
public class SSOApplication extends BaseApplication
{

	public static void main( String[] args )
	{
		TimeZone.setDefault( TimeZone.getTimeZone( "UTC" )  );
		SpringApplication.run( SSOApplication.class, args );
	}

}
