package com.example.bestpractices.configuration.security;

import com.example.bestpractices.configuration.security.dto.Token;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Date;

@Component
@Log4j2
public class JWTUtil {

	@Value("${jwt.jwt_secret}")
	private String secret;

	static ObjectMapper mapper = new ObjectMapper();

	public static Token decodeToken(String token){
		try {
			String[] chunks = token.split("\\.");

			Base64.Decoder decoder = Base64.getUrlDecoder();

			String header = new String(decoder.decode(chunks[0]));
			String payload = new String(decoder.decode(chunks[1]));


			Token tokenData = mapper.readValue(payload, Token.class);
			return tokenData;
		} catch (JsonProcessingException e) {
			log.error(String.format("Token could not be decoded: %s", e.getMessage()));
			return null;
		}
	}
	
	public static String generateToken(String subject, String issuer, Long expiration, String secret) {
		return Jwts.builder()
				.setSubject(subject)
				.setIssuer(issuer)
				.setExpiration(new Date(System.currentTimeMillis() + expiration))
				.signWith(SignatureAlgorithm.HS512, secret.getBytes())
				.compact();
	}
}
