package com.koc.user.application.service;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.experimental.UtilityClass;

import java.util.Base64;
import java.util.Date;

@UtilityClass
public class JwtProvider {

    private static String secretKey = "testPassword";

    public static String createToken(String subject, int tokenValidTime) {
        Date now = new Date();
        Date expiration = new Date(System.currentTimeMillis() + (60000L * tokenValidTime));

        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setIssuer("test")
                .setIssuedAt(now)
                .setExpiration(expiration)
                .setSubject(subject)
                .signWith(SignatureAlgorithm.HS256, Base64.getEncoder().encodeToString(secretKey.getBytes()))
                .compact();
    }


    public static Claims parseJwtToken(String token) {
        token = bearerRemove(token);
        return Jwts.parser()
                .setSigningKey(Base64.getEncoder().encodeToString(secretKey.getBytes()))
                .parseClaimsJws(token)
                .getBody();
    }


    private static String bearerRemove(String token) {
        return token.substring("Bearer ".length());
    }
}