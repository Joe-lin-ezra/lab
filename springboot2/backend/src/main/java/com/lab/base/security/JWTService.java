package com.lab.base.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Service
public class JWTService
{
	private final Integer JWT_TOKEN_VALIDITY = 5 * 60 * 60;
	private final String secretKey;

	public JWTService( @Value( "${jwt.secret}" ) String secretKey )
	{
		this.secretKey = secretKey;
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

	public <T> T getClaimFromToken( String token, Function<Claims, T> claimsResolver )
	{
		Claims claims = getAllClaims( token );
		return claimsResolver.apply( claims );
	}
}
