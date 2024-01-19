package com.lab.sso.service;

import com.lab.base.dmo.User;
import com.lab.base.dto.sso.request.LoginDTO;
import com.lab.base.dto.sso.request.RegisterDTO;
import com.lab.sso.repository.criteria.UserCriteriaRepository;
import com.lab.sso.repository.jpql.UserRepository;
import com.lab.sso.security.JWTService;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.UUID;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith( MockitoJUnitRunner.class)
@FixMethodOrder
public class AuthServiceTest
{
	@Mock
	private UserRepository userRepository;

	@Mock
	private UserCriteriaRepository userCriteriaRepository;

	@Mock
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Mock
	private JWTService jwtService;

	@InjectMocks
	private AuthService authService;

	private User user;

	@Before
	public void setUp() throws Exception
	{
		MockitoAnnotations.openMocks( this );

		user = new User();
		user.setUuid( UUID.randomUUID() );
		user.setEmail( "ttt@mail.com" );
		user.setPassword( "encrypted" );
		user.setToken("token");
	}

	@Test
	public void register()
	{
		RegisterDTO registerDTO = new RegisterDTO("ttt@mail.com", "ttt");

		when(userRepository.save( user )).thenReturn( user );
		when(bCryptPasswordEncoder.encode( "ttt" )).thenReturn( "token" );

		Boolean result = authService.register( registerDTO );

		assertEquals( true, result );
	}

	@Test
	public void isMailExist()
	{
		when(userRepository.findByEmail( "ttt@mail.com" )).thenReturn( this.user );

		Boolean result1 = authService.isMailExist( "ttt@mail.com" );
		Boolean result2 = authService.isMailExist( "dsfasfd@mail.com" );

		assertEquals( true, result1 );
		assertEquals( false, result2 );
	}

	@Test
	public void login()
	{
		// successful login
		LoginDTO loginDTO = new LoginDTO( "ttt@mail.com", "ttt");

		when(userRepository.findByEmail( "ttt@mail.com" )).thenReturn( user );
		when(bCryptPasswordEncoder.encode( loginDTO.getPassword() )).thenReturn( "encrypted" );

		String token = authService.login( loginDTO );
		assertNotNull(token);

		//	user not found, and then return null
		loginDTO = new LoginDTO( "oao@mail.com", "ttt");
		when(userRepository.findByEmail( "oao@mail.com" )).thenReturn( null );
		token = authService.login( loginDTO );
		assertNull( token );
	}

	@Test
	public void logout()
	{

	}

	@Test
	public void checkJwt()
	{

	}
}