package com.qnadeel.springdemo.security.bookrentalsystem.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    private final SecretKey secretKey;


    public JwtService() {
        String key = "2XwD8eRxMvT4n9VpLF9UyBG5cD7NlKmKqY6fO9qxMzU="; // Example key (should be 256 bits and secure)
        this.secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(key));
    }

    public String generateToken(String email, String username) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", username);

        return Jwts
                .builder()
                .claims(claims)
                .subject(email)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 60 * 60 * 60 * 100))
                .signWith(secretKey)
                .compact();
    }


    public String extractUserName(String jwtToken) {
        return extractClaim(jwtToken, claims -> claims.get("username", String.class));
    }

    private <T> T extractClaim(String token,
                               Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().verifyWith(secretKey)
                .build().parseSignedClaims(token).getPayload();
    }

    public boolean validateToken(String jwtToken, UserDetails userDetails) {
        final String username = extractUserName(jwtToken);
        return (username.equals(userDetails.getUsername())
                && !isTokenExpired(jwtToken));
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token)
                .before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

}
