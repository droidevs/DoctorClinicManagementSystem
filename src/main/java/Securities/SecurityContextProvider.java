/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Securities;

import Dtos.RoleDto;
import Services.JwtService;
import Services.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import jakarta.inject.Inject;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.core.SecurityContext;

import java.util.Set;
import java.util.UUID;

public class SecurityContextProvider {

    @Inject
    private JwtService service;
    
    @Inject
    private UserService userService;

    public SecurityContextProvider() {
    }

    public void setContext(ContainerRequestContext ctx, Claims claims, String token) {

        try {
            // Extract user identity
            UUID userId = service.extractUserId(claims);
            String username = service.extractUsername(claims);
            
            Set<RoleDto> roles = userService.getUserRoles(userId);
            // Build principal
            JwtPrincipal principal = new JwtPrincipal(userId, username, roles);

            // Build security context
            SecurityContext securityContext = new JwtSecurityContext(principal, ctx.getSecurityContext().isSecure());

            ctx.setSecurityContext(securityContext);

        } catch (JwtException e) {
            // Failed JWT parsing -> do nothing (context remains unauthenticated)
        }
    }
}

