/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import Config.JwtConfig;
import Dtos.RoleDto;
import Dtos.UserDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import javax.crypto.SecretKey;

import io.jsonwebtoken.Claims;

/**
 * Interface for JWT token generation, validation, and claim extraction
 */
public interface JwtService {
    
    /**
     * Generate a JWT token with the given subject and claims
     * 
     * @param subject The subject of the token (typically user ID)
     * @param claims Additional claims to include in the token
     * @return The generated JWT token as a string
     */
    String generateToken(String subject, Map<String, Object> claims);
    
    /**
     * Generate a JWT token for a user with standard claims
     * 
     * @param user The user DTO containing user information
     * @return The generated JWT token as a string
     */
    String generateUserToken(UserDto user);
    
    /**
     * Validate and parse a JWT token
     * 
     * @param token The JWT token to validate
     * @return A Jws object containing the claims if valid
     * @throws io.jsonwebtoken.JwtException if the token is invalid
     */
    Jws<Claims> validateToken(String token);
    
    /**
     * Extract claims from a JWT token
     * 
     * @param token The JWT token
     * @return The claims from the token
     */
    Claims getClaimsFromToken(String token);
    
    /**
     * Extract the subject from a JWT token
     * 
     * @param token The JWT token
     * @return The subject of the token
     */
    String extractSubject(String token);
    
    /**
     * Extract the username from token claims
     * 
     * @param claims The JWT claims
     * @return The username
     */
    String extractUsername(Claims claims);
    
    /**
     * Extract the username directly from a token
     * 
     * @param token The JWT token
     * @return The username
     */
    String extractUsername(String token);
    
    /**
     * Extract the user ID from token claims
     * 
     * @param claims The JWT claims
     * @return The user ID as UUID
     */
    UUID extractUserId(Claims claims);
    
    /**
     * Extract the user ID directly from a token
     * 
     * @param token The JWT token
     * @return The user ID as UUID
     */
    UUID extractUserId(String token);
    
    /**
     * Extract the issuer from a token
     * 
     * @param token The JWT token
     * @return The issuer of the token
     */
    String extractIssuer(String token);
    
    /**
     * Check if a token is valid without throwing exceptions
     * 
     * @param token The JWT token to check
     * @return true if the token is valid, false otherwise
     */
    boolean isValidToken(String token);
    
    /**
     * Check if a token is expired
     * 
     * @param token The JWT token to check
     * @return true if the token is expired, false otherwise
     * @throws SecurityException if the token is invalid
     */
    boolean isTokenExpired(String token);
    
    /**
     * Refresh an existing token with a new expiration date
     * 
     * @param oldToken The old token to refresh
     * @return A new token with refreshed expiration
     */
    String refreshToken(String oldToken);
    
    /**
     * Extract roles with their permissions from token claims
     * 
     * @param claims The JWT claims
     * @return Set of RoleDto objects with permissions
     */
    //Set<RoleDto> extractRolesWithPermissions(Claims claims);
    
    /**
     * Generate a new secret key for JWT signing
     * 
     * @return Base64 encoded secret key
     */
    static String generateSecretKey() {
        Key key = Keys.secretKeyFor(io.jsonwebtoken.SignatureAlgorithm.HS256);
        return io.jsonwebtoken.io.Encoders.BASE64.encode(key.getEncoded());
    }
}