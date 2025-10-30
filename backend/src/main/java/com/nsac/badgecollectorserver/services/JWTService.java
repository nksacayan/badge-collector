package com.nsac.badgecollectorserver.services;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.nsac.badgecollectorserver.models.UserDTO;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;


@Service
public class JWTService {

    private final Key key;

    public JWTService(@Value("${jwt.secret}") String jwtSecret) {
        this.key = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
    }

    public String generateTokenWithUserId(UserDTO userDTO) {
        return Jwts.builder()
            .setSubject(userDTO.getId().toString())
            .setIssuedAt(new Date())
            .signWith(key, SignatureAlgorithm.HS256)
            .compact();
    }

    public String extractTokenSubject(String token) {
        return Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token)
            .getBody()
            .getSubject();
    }
}