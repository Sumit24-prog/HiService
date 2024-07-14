package org.example.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.example.exception.CustomTokenException;
import org.example.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.sql.SQLOutput;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expiration;

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);

    public String generateToken(Authentication authentication) {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        Date exp = new Date(nowMillis + expiration);
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        String userId = userDetails.getUserId();

        return Jwts.builder()
                .setSubject(userId)
                .setIssuedAt(now)
                .setExpiration(exp)
                .signWith(getSigningKey(), SignatureAlgorithm.HS512)
                .compact();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        try {
            final String userIdFromToken = getUsernameFromToken(token);
            CustomUserDetails customUserDetails = (CustomUserDetails) userDetails;
            boolean valid = (userIdFromToken.equals(customUserDetails.getUserId()) && !isTokenExpired(token));
            logger.info("Token validation result for user {}: {}", customUserDetails.getUsername(), valid);
            return valid;
        } catch (SignatureException e) {
            logger.error("Invalid JWT signature for token: {}", token, e);
        } catch (Exception e) {
            logger.error("Token validation error: {}", e.getMessage(), e);
        }
        return false;
    }

    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    private <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims;
        try {
            claims = getAllClaimsFromToken(token);
        } catch (CustomTokenException e) {
            // Log the exception or handle it as needed
            System.out.println("Error parsing token: " + e.getMessage());
            return null; // or throw e;
        }
        if (claims == null) {
            // Handle the case where claims is null
            System.out.println("Claims are null, token might be invalid.");
            return null; // or throw a new CustomTokenException("Claims are null.");
        }
        return claimsResolver.apply(claims);
    }

    public Claims getAllClaimsFromToken(String token) {
        try {
            Key key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
            Jws<Claims> claimsJws = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return claimsJws.getBody();
        } catch (ExpiredJwtException e) {
            logger.warn("Expired JWT token: {}", token, e);
        } catch (UnsupportedJwtException e) {
            logger.error("Unsupported JWT token: {}", token, e);
        } catch (MalformedJwtException e) {
            logger.error("Malformed JWT token: {}", token, e);
        } catch (SignatureException e) {
            logger.error("Invalid JWT signature for token: {}", token, e);
        } catch (IllegalArgumentException e) {
            logger.error("JWT token compact of handler are invalid: {}", token, e);
        } catch (JwtException e) {
            logger.error("JWT parsing failed: {}", e.getMessage(), e);
        }
        return null;
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }
}