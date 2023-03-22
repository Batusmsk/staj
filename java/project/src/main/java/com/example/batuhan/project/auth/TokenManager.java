package com.example.batuhan.project.auth;

import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class TokenManager {
	
	private static final String secretKey = "batuhanSmsk";
	private static final Integer validity = 5 * 60 * 1000;
	Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
	
	public String generateToken(String email) {
		
		String jws = Jwts.builder().setSubject(email)
				.setIssuer("batuhan")
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + validity))
				.signWith(key)
				.compact();
		return jws;
	}
	
	public boolean tokenValidate(String token) {
		if(getEmailToken(token) != null && isExpired(token)) {
			return true;
		}
		return false;
	}
	
	
	
	public String getEmailToken(String token) {
		Claims claims = getClaims(token);
		return claims.getSubject();
	}
	
	public Boolean isExpired(String token) {
		Claims claims = getClaims(token);
		return claims.getExpiration().after(new Date(System.currentTimeMillis()));
	}
	private Claims getClaims(String token) {
		return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
	}
}
