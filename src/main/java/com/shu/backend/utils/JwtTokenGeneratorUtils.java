package com.shu.backend.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;

import javax.crypto.SecretKey;
import java.util.Date;

public class JwtTokenGeneratorUtils {

    private static final SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private static final long EXPIRATION_TIME_MS = 3600000 * 24; // 24 hour

    public static String generateJwtToken(String s) {
        Date now = new Date();
        Date expirationTime = new Date(now.getTime() + EXPIRATION_TIME_MS);

        String jwtToken = Jwts.builder()
                .setSubject(s)
                .setIssuedAt(now)
                .setExpiration(expirationTime)
                .signWith(SECRET_KEY)
                .compact();
        return jwtToken;
    }

    public static Claims parserJwtToken(String jwtToken) throws SignatureException {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(jwtToken)
                .getBody();
        return claims;
    }

    public static boolean validateJwtToken(String jwtToken, String s) throws SignatureException {
        Claims claims = parserJwtToken(jwtToken);
        String subject = claims.getSubject();
        Date expiration = claims.getExpiration();

        // 在此处可以根据需要进行其他逻辑处理

        Date now = new Date();
        return s.equals(subject) && expiration.before(now);
    }
}
