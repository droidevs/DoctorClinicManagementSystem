/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services.impl;

import Config.JwtConfig;
import Dtos.RoleDto;
import Dtos.UserDto;
import Services.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import javax.crypto.SecretKey;

/**
 *
 * @author admin
 */
public class JwtServiceImpl implements JwtService {
    
    
    private SecretKey signingKey;

    public JwtServiceImpl() {
        byte[] keyBytes = Decoders.BASE64.decode(JwtConfig.SECRET);
        this.signingKey = Keys.hmacShaKeyFor(keyBytes);
    }

    @Override
    public String generateToken(String subject, Map<String, Object> claims) {
        Instant now = Instant.now();
        
        var expDate = now.plusSeconds(JwtConfig.EXPIRATION_SECONDS);
                
        return Jwts.builder()
                .subject(subject)
                .issuer(JwtConfig.ISSUER)
                .claims(claims)
                .issuedAt(Date.from(now))
                .expiration(Date.from(expDate))
                .signWith(signingKey, Jwts.SIG.HS256)
                .compact();
    }
    
    
    public String generateUserToken(UserDto user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", user.id());
        claims.put("username", user.email());
        claims.put("roles", List.of(user.role().name()));
        
        return generateToken(user.id().toString(), claims);
    }
    
    public Jws<Claims> validateToken(String token) {
        return Jwts.parser()
                .verifyWith(signingKey)
                .requireIssuer(JwtConfig.ISSUER)
                .build()
                .parseSignedClaims(token);        
    }
    
    public Claims getClaimsFromToken(String token) {
        return validateToken(token).getPayload();
    }
    
    public String extractSubject(String token) {
        return getClaimsFromToken(token).getSubject();
    }
    
    public String extractUsername(Claims claims) {
        return claims.get("username", String.class);
    }
    public String extractUsername(String token) {
        return getClaimsFromToken(token).get("username", String.class);
    }
    
    public UUID extractUserId(Claims claims) {
        return claims.get("userId", UUID.class);
    }
    
    public UUID extractUserId(String token) {
        return getClaimsFromToken(token).get("userId", UUID.class);
    }
    
    /*
    public List<String> extractRoles(String token) {
        return getClaimsFromToken(token).get("roles", List.class);
    }
    
    public List<String> extractRoles(Claims claims) {
        return claims.get("roles", List.class);
    }*/
    
    
    public String extractIssuer(String token) {
        return getClaimsFromToken(token).getIssuer();
    }
    
    public boolean isValidToken(String token) {
        try {
            validateToken(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean isTokenExpired(String token) {
        try {
            Claims claims = getClaimsFromToken(token);
            return  claims.getExpiration().before(new Date());
        } catch (JwtException e) {
            throw new SecurityException("Invalid token", e);
        }
    }
    
    public String refreshToken(String oldToken) {
        Claims claims = getClaimsFromToken(oldToken);
        
        return Jwts.builder()
                .issuer(JwtConfig.ISSUER)
                .subject(claims.getSubject())
                .claims(claims)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + JwtConfig.EXPIRATION_SECONDS * 1000))
                
                .signWith(signingKey, Jwts.SIG.HS256)
                .compact();
    }
    
    public static String generateSecretKey() {
        Key key = Jwts.SIG.HS256.key().build();
        return Encoders.BASE64.encode(key.getEncoded());
    }
}
