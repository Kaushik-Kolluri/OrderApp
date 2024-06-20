package com.indus.training.webtoken;

import java.time.Instant;
import java.util.Base64;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	
	private static final String mySecretKey = "A8431BEDF7932F12E25A801E9A43E0F0938DEC1362D74AC3A9825266FA6FAE5B"; // this is the secret key but it is in string format.
	private static final Long validity = TimeUnit.MINUTES.toMillis(30); // converting minutes to milliseconds.
	
	public String generateToken(UserDetails userDetails) {
		
		return Jwts.builder()
				.subject(userDetails.getUsername()) // says for which user we are creating the JWT.
				.issuedAt(Date.from(Instant.now())) // says the key is issued at current date and time.
				.expiration(Date.from(Instant.now().plusMillis(validity)))
				.signWith(generateDecodedSecretKey())
				.compact();
				
		
	}
	
	private SecretKey generateDecodedSecretKey() {
		
		byte[] decodedSecretKey = Base64.getDecoder().decode(mySecretKey); // this is not the key itself, it is just the byte array.
		return Keys.hmacShaKeyFor(decodedSecretKey); // this is the secret key decoded from the byte array above.
	}

	public String extractUserName(String jwt) {
		// TODO Auto-generated method stub
		Claims claims = getClaims(jwt);
		
		return claims.getSubject();
	}
	
	private Claims getClaims(String jwt) {
		
		return Jwts.parser()
				.verifyWith(generateDecodedSecretKey())
				.build()
				.parseSignedClaims(jwt)
				.getPayload();
		
	}

	public boolean isTokenValid(String jwt) {
		Claims cliams = getClaims(jwt);
		return cliams.getExpiration().after(Date.from(Instant.now()));
	}
}
