/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Producers;

import Securities.JwtPrincipal;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.SecurityContext;
import java.security.Principal;
import java.util.Optional;
import java.util.UUID;

/**
 *
 * @author admin
 */
public class SecurityContextProducer {
    
    
    @Inject
    @Context
    private SecurityContext securityContext;
    
    @Produces
    @RequestScoped
    public SecurityContext getSecurityContext() {
        return securityContext;
    }
    
    @Produces
    @RequestScoped
    public Optional<JwtPrincipal> getJwtPrincipal() {
        Principal principal = securityContext.getUserPrincipal();
        if (principal instanceof JwtPrincipal) {
            return Optional.of((JwtPrincipal) principal);
        }
        return Optional.empty();
    }
    
}
