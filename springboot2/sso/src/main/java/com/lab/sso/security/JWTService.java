package com.lab.sso.security;

import com.lab.sso.repository.jpql.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;

@Service
public class JWTService
{
	private final Integer JWT_TOKEN_VALIDITY = 5 * 60 * 60;
	private final String secretKey;
	private final UserRepository userRepository;

	public JWTService( @Value( "${jwt.secret}" ) String secretKey, UserRepository userRepository )
	{
		this.secretKey = secretKey;
		this.userRepository = userRepository;
	}

	public String generateToken( Map<String, String> claimsContent )
	{
		return Jwts.builder()
				.setClaims( claimsContent )
				.setSubject( "user identity" )
				.setIssuedAt( new Date( System.currentTimeMillis() ) )
				.setExpiration( new Date( System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000 ) )
				.signWith( Keys.hmacShaKeyFor( secretKey.getBytes() ), SignatureAlgorithm.HS512 )
				.compact();
	}

	public Boolean validateToken( String token ) throws ExecutionException, InterruptedException
	{
		return userRepository.existsByToken( token ) && !isTokenExpired( token );
	}

	public Claims getClaims( String token )
	{
		return Jwts.parserBuilder()
				.setSigningKey( Keys.hmacShaKeyFor( secretKey.getBytes() ) )
				.build()
				.parseClaimsJws( token )
				.getBody();
	}

	private Claims getAllClaims( String token )
	{
		SecretKey key = Keys.hmacShaKeyFor( secretKey.getBytes() );
		return Jwts.parserBuilder()
				.setSigningKey( key )
				.build()
				.parseClaimsJws( token )
				.getBody();
	}

	private Boolean isTokenExpired( String token )
	{
		Date expiration = getClaimFromToken( token, Claims::getExpiration );
		return expiration.before( new Date() );
	}

	public <T> T getClaimFromToken( String token, Function<Claims, T> claimsResolver )
	{
		Claims claims = getAllClaims( token );
		return claimsResolver.apply( claims );
	}
}
