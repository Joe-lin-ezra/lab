package com.lab.sso.controller;

import com.lab.base.dto.sso.request.JwtCheckDTO;
import com.lab.base.dto.sso.request.LoginDTO;
import com.lab.base.dto.sso.request.LogoutDTO;
import com.lab.base.dto.sso.request.RegisterDTO;
import com.lab.base.dto.sso.response.BasicMessage;
import com.lab.base.dto.sso.response.LoginResponse;
import com.lab.sso.security.JWTService;
import com.lab.sso.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
//import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping( "sso" )
public class AuthController
{
	private AuthService authService;

	private JWTService jwtService;

	@Autowired
	public AuthController( AuthService authService, JWTService jwtService )
	{
		this.authService = authService;
		this.jwtService = jwtService;
	}

	@PostMapping( "login" )
//	public Mono<Object> login( @RequestBody @Validated LoginDTO loginDTO ) throws Exception
	public ResponseEntity<Object> login( @RequestBody @Validated LoginDTO loginDTO ) throws Exception
	{
		String token = authService.login( loginDTO );

		if( Objects.isNull( token ) )
		{
//			return Mono.just( new BasicMessage( false, "Email or Password is wrong." ) );
			return ResponseEntity.status( HttpStatus.UNAUTHORIZED ).body( new BasicMessage( false,
			                                                                                "Email or Password is wrong." ) );
		}

//		return Mono.just( new LoginResponse( true, "success", token ) );
		return ResponseEntity.status( HttpStatus.OK )
							.body( new LoginResponse( true, "success", "bearer token", token ) );
	}

	@PostMapping( "logout" )
//	public Mono<Object> logout( @RequestHeader( "Authorization" ) String authorization, @RequestBody @Validated LogoutDTO logoutDTO ) throws Exception
	public ResponseEntity<Object> logout( @RequestHeader( "Authorization" ) String authorization ) throws Exception
	{
		authService.logout( authorization );

//		return Mono.just( new BasicMessage( true, "Logout succeeded." ) );
		return ResponseEntity.status( HttpStatus.NO_CONTENT ).body( new BasicMessage( true, "Logout succeeded." ) );
	}

	@PostMapping( "register" )
//	public Mono<Object> register( @RequestBody @Validated RegisterDTO registerDTO ) throws Exception
	public ResponseEntity<Object> register( @RequestBody @Validated RegisterDTO registerDTO ) throws Exception
	{
		if( authService.isMailExist( registerDTO.getEmail() ) )
		{
//			return Mono.just( new BasicMessage( false, "Email has been used." ) );
			return ResponseEntity.status( HttpStatus.CONFLICT ).body( new BasicMessage( false, "Email has been used." ) );
		}

		authService.register( registerDTO );

//		return Mono.just( new BasicMessage( true, "Registration succeeded." ) );
		return ResponseEntity.status( HttpStatus.CREATED ).body( new BasicMessage( true, "Registration succeeded." ) );
	}

	@PostMapping( "check" )
//	public Mono<Object> checkJwt( @RequestBody @Validated JwtCheckDTO jwtCheckDTO ) throws ExecutionException, InterruptedException
	public ResponseEntity<Object> checkJwt( @RequestBody @Validated JwtCheckDTO jwtCheckDTO ) throws Exception
	{
		Boolean result = jwtService.validateToken( jwtCheckDTO.getToken() );
//		return Mono.just( new BasicMessage( result, "" ) );
		return ResponseEntity.status( result ? HttpStatus.OK : HttpStatus.UNAUTHORIZED ).body( new BasicMessage( result, "" ) );
	}
}
