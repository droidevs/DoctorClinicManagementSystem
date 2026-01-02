/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Config;

import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import static javax.crypto.Cipher.SECRET_KEY;

/**
 *
 * @author admin
 */
public class JwtConfig {
    
    
    public static final String ISSUER = "clinic-app";
    public static final long EXPIRATION_SECONDS = 60 * 60 * 6; // 6 hours
    public static final String SECRET = "CHANGE_THIS_SECRET_KEY_256_BIT";
    
    public static final long EXPIRATION_MS = 24 * 60 * 60 * 1000; // 24 hours
    
    public static final String SECRET_KEY = generateSecretKey();
    
    private static String generateSecretKey() {
        Key key = Keys.secretKeyFor(io.jsonwebtoken.SignatureAlgorithm.HS256);
        return Encoders.BASE64.encode(key.getEncoded());
    }
    
    // Get signing key from base64 encoded secret
    public static Key getSigningKey() {
        byte[] keyBytes = io.jsonwebtoken.io.Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
