package com.example.demo.cors;

import com.example.demo.dto.TokenDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.Map;


@Component
public class JwtTokenProvider {
    private static final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);
    private final String secretKey = "m+gQ3P9qlOc7KZZay4K6M2N6eTeZPz5D4gZzgLrKoQI=";
    private final long validityInMilliseconds = 3600000;
    private final Key key;

    public JwtTokenProvider() {
        this.key = Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    public String createToken(String username, TokenDTO token) {
        Claims claims = Jwts.claims().setSubject(username);
        claims.put("role", token);
        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(key,SignatureAlgorithm.HS256)
                .compact();
    }

    public Claims getClaims(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

        } catch (ExpiredJwtException e) {
            System.err.println("Token expired: " + e.getMessage());
            throw e;
        } catch (MalformedJwtException e) {
            System.err.println("Malformed token: " + e.getMessage());
            throw e;
        } catch (SignatureException e) {
            System.err.println("Invalid signature: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            System.err.println("Other JWT exception: " + e.getMessage());
            throw e;
        }

    }

    public boolean validateToken(String token) {
        try {
            getClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    public String getUsername(String token) {
        return getClaims(token).getSubject();
    }

    public TokenDTO getUserInfo(String token) {
        Claims claims = getClaims(token);

        Map<String, Object> roleMap = claims.get("role", Map.class);

        if (roleMap == null) {
            throw new RuntimeException("Missing 'role' in JWT claims.");
        }

        return new TokenDTO(
                safeToString(roleMap.get("accId")),
                safeToString(roleMap.get("accName")),
                safeToString(roleMap.get("fullname")),
                safeToString(roleMap.get("permission"))
        );
    }

    // Helper để tránh gọi toString() trên null
    private String safeToString(Object obj) {
        return obj != null ? obj.toString() : null;
    }

}
