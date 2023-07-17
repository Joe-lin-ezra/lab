package com.lab.sso.service;

import com.lab.base.dmo.User;
import com.lab.base.dto.sso.request.LoginDTO;
import com.lab.base.dto.sso.request.LogoutDTO;
import com.lab.base.dto.sso.request.RegisterDTO;
import com.lab.sso.repository.criteria.UserCriteriaRepository;
import com.lab.sso.repository.jpql.UserRepository;
import com.lab.sso.security.JWTService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.ExecutionException;

@Service
@Transactional( propagation = Propagation.REQUIRES_NEW )
public class AuthService
{
	private UserRepository userRepository;

	private UserCriteriaRepository userCriteriaRepository;

	private BCryptPasswordEncoder bCryptPasswordEncoder;

	private JWTService jwtService;

	@Autowired
	public AuthService( UserRepository userRepository, UserCriteriaRepository userCriteriaRepository, BCryptPasswordEncoder bCryptPasswordEncoder, JWTService jwtService )
	{
		this.userRepository = userRepository;
		this.userCriteriaRepository = userCriteriaRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.jwtService = jwtService;
	}

	public Boolean isMailExist( String email )
	{
		//		return Objects.nonNull( userRepository.findByEmail( email ).toFuture().get() );
		return Objects.nonNull( userRepository.findByEmail( email ) );
	}

	public Boolean register( RegisterDTO registerDTO )
	{
		UUID uuid = UUID.randomUUID();
		String encryptedPassword = bCryptPasswordEncoder.encode( registerDTO.getPassword() );

		User user = new User();
		user.setUuid( uuid );
		user.setEmail( registerDTO.getEmail() );
		user.setPassword( encryptedPassword );

		userRepository.save( user );
		return true;
	}

	/**
	 * Return jwt token when succeeded or null when failed.
	 */
	public String login( LoginDTO loginDTO )
	{
		//		User user = userRepository.findByEmail( loginDTO.getEmail() ).toFuture().get();
		User user = userRepository.findByEmail( loginDTO.getEmail() );
		if( Objects.isNull( user ) )
		{
			return null;
		}

		Map<String, String> claims = new HashMap<>();
		claims.put( "id", user.getUuid().toString() );

		String token = jwtService.generateToken( claims );
		user.setToken( token );

		return token;
	}

	public Boolean logout( String authorization )
	{
		String token = authorization.substring( 7 );
		Claims claims = jwtService.getClaims( token );
		//		User user = userRepository.findByUuid( claims.get( "id", UUID.class ) ).toFuture().get();
		User user = userRepository.findByUuid( UUID.fromString( claims.get( "id", String.class ) ) );

		user.setToken( null );

		return true;
	}

	public Boolean checkJwt( String token )
	{
		return true;
	}
}
