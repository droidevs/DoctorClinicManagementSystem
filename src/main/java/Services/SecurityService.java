/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import Enums.Role;
import Securities.JwtPrincipal;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.security.enterprise.SecurityContext;
import jakarta.security.enterprise.identitystore.IdentityStore;
import jakarta.security.enterprise.identitystore.PasswordHash;
import java.nio.file.attribute.GroupPrincipal;
import java.security.Principal;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@ApplicationScoped
public class SecurityService {
    
    @Inject
    private SecurityContext securityContext;
    
    @Inject
    private PasswordHash passwordHash;
    
    /**
     * Get current authenticated user
     */
    public Optional<JwtPrincipal> getCurrentUser() {
        Principal principal = securityContext.getCallerPrincipal();
        if (principal instanceof JwtPrincipal) {
            return Optional.of((JwtPrincipal) principal);
        }
        return Optional.empty();
    }
    
    /**
     * Get current user ID
     * @return 
     */
    public Optional<UUID> getCurrentUserId() {
        return getCurrentUser().map(JwtPrincipal::getUserId);
    }
    
    /**
     * Check if current user has role
     */
    public boolean hasRole(Role role) {
        return securityContext.isCallerInRole(role.name());
    }
    
    /**
     * Check if user is authenticated
     */
    public boolean isAuthenticated() {
        return securityContext.getCallerPrincipal() != null;
    }
    
    /**
     * Get all caller groups (roles)
     */
    public Set<String> getCallerGroups() {
        return securityContext.getPrincipalsByType(GroupPrincipal.class)
                .stream()
                .map(GroupPrincipal::getName)
                .collect(Collectors.toSet());
    }
}