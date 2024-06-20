package com.indus.training.jwt;

import javax.crypto.SecretKey;

import org.junit.jupiter.api.Test;

import io.jsonwebtoken.Jwts;
import jakarta.xml.bind.DatatypeConverter;

public class JwtSecretMakerTest {
	
	@Test
	public void generateSecretKey() {
		
		SecretKey key =  Jwts.SIG.HS256.key().build(); // builds the secret key.
		String encodedKey = DatatypeConverter.printHexBinary(key.getEncoded()); // displays in the string format.
		System.out.println("Key: " + encodedKey);
		
	}

}
