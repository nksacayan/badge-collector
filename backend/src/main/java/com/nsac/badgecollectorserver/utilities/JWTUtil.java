package com.nsac.badgecollectorserver.utilities;

import org.springframework.beans.factory.annotation.Value;

import com.nsac.badgecollectorserver.models.UserDTO;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;


public class JWTUtil {

    @Value("${jwt.secret}")
    private String jwtSecret;

    public String generateToken(UserDTO userDTO) {
        Key key = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));

        return Jwts.builder()
            .setSubject(userDTO.getName())
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 hour
            .signWith(key, SignatureAlgorithm.HS256)
            .compact();
    }


    // public boolean validateToken(String token, UserDTO userDTO) {
    //     final String username = extractUsername(token);
    //     return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    // }
}
