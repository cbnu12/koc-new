package com.koc.user.application.service;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.experimental.UtilityClass;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;

@UtilityClass
public class JwtProvider {

    private static final String SECRET_KEY = "testPassword";

    public static String createToken(String subject, int tokenValidTime) {
        Date now = new Date();
        Date expiration = new Date(System.currentTimeMillis() + (60000L * tokenValidTime));
        SecretKey key = Keys.hmacShaKeyFor(Base64.getEncoder().encode(SECRET_KEY.getBytes()));

        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setIssuer("test")
                .setIssuedAt(now)
                .setExpiration(expiration)
                .setSubject(subject)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public static Claims parseJwtToken(String token) {
        token = bearerRemove(token);
        return Jwts.parserBuilder()
                .setSigningKey(Base64.getEncoder().encodeToString(SECRET_KEY.getBytes()))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private static String bearerRemove(String token) {
        return token.substring("Bearer ".length());
    }
}