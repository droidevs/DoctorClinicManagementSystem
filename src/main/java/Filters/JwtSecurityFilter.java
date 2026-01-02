/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Filters;

import Securities.SecurityContextProvider;
import Services.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import jakarta.annotation.Priority;
import jakarta.inject.Inject;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;
import java.io.IOException;
import java.time.Instant;
import java.util.Map;

/**
 *
 * @author admin
 */
@Provider
@Priority(FilterPriority.JWT_SECURITY)
public class JwtSecurityFilter implements ContainerRequestFilter {

    @Inject
    private JwtService jwtService;
    
    @Inject
    private SecurityContextProvider securityContextProvider;
    
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        
        if(isPublicPath(requestContext)) {
            return;
        }
        
        String authHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            abortUnauthorized(requestContext, "Messing or invalid Authorization header");
            
            return;
        }
        
        String token = authHeader.substring("Bearer ".length()).trim();
        
        try {
            Jws<Claims> jws = jwtService.validateToken(token);
            Claims claims = jws.getPayload();
            securityContextProvider.setContext(requestContext, claims, token);
            
        } catch(ExpiredJwtException e) {
            abortUnauthorized(requestContext, "Token expired. Please refresh or login again.");
        } catch (JwtException e) {
            abortUnauthorized(requestContext, "Invalid token: " + e.getMessage());
        }
    }
    
    
    private boolean isPublicPath(ContainerRequestContext context) {
        String path = context.getUriInfo().getPath();
        return path.startsWith("/auth/") ||
                path.startsWith("/public/") ||
                path.startsWith("/health");
    }
    
    private void abortUnauthorized(ContainerRequestContext context, String message) {
        Map<String, Object> error = Map.of(
            "error", "Unauthorized",
            "message", message,
            "timestamp", Instant.now().toString(),
            "path", context.getUriInfo().getPath()
        );
        
        context.abortWith(
                Response.status(Response.Status.UNAUTHORIZED)
                .entity(error)
                .build()
                );
                
    }
}
