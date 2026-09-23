package com.duaratrust.duaratrust.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {

    private final SecretKey key = Jwts.SIG.HS256.key().build();
    private final long expirationMs = 3600000;

    public String generateToken(String phoneNumber) {
        return Jwts.builder()
                .subject(phoneNumber)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expirationMs))
                .signWith(key)
                .compact();
    }

    public String extractPhoneNumber(String token) {
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public boolean isTokenvalid(String token) {
        try {
            extractPhoneNumber(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}