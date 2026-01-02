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

@RequestScoped
public class SecurityContextProducer {

    @Inject
    @Context
    private SecurityContext securityContext;

    /**
     * Produces the CDI injectable SecurityContext for services
     */
    @Produces
    @RequestScoped
    public SecurityContext produceSecurityContext() {
        return securityContext;
    }

    /**
     * Produces the Principal as Optional<JwtPrincipal>
     */
    @Produces
    @RequestScoped
    public Optional<JwtPrincipal> produceJwtPrincipal() {
        Principal principal = securityContext != null ? securityContext.getUserPrincipal() : null;
        if (principal instanceof JwtPrincipal jwtPrincipal) {
            return Optional.of(jwtPrincipal);
        }
        
        return Optional.empty();
    }
}
