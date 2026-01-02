/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Securities;

import Services.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.core.SecurityContext;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author admin
 */
public class SecurityContextProvider {

    private JwtService service;

    public void setContext(ContainerRequestContext ctx, Claims claims, String token) {

        try {
            UUID userId = service.extractUserId(claims);
            String username = service.extractUsername(claims);
            List<String> roles = service.extractRoles(claims);

            JwtPrincipal principale = new JwtPrincipal(userId, username, roles, token);
            SecurityContext context = new JwtSecurityContext(principale, ctx.getSecurityContext().isSecure());
            
            ctx.setSecurityContext(context);
        } catch (JwtException e) {
        }
    }
}
