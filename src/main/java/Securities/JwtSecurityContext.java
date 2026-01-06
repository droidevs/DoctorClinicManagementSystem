/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Securities;

import jakarta.ws.rs.core.SecurityContext;
import java.security.Principal;

/**
 *
 * @author admin
 */

public class JwtSecurityContext implements SecurityContext {

    private final JwtPrincipal principal;
    private final boolean secure;

    public JwtSecurityContext(JwtPrincipal principal, boolean secure) {
        this.principal = principal;
        this.secure = secure;
    }

    @Override
    public Principal getUserPrincipal() {
        return principal;
    }

    /**
     * Role-based check (kept for compatibility)
     * Prefer permission-based checks instead.
     */
    @Override
    public boolean isUserInRole(String role) {
        return principal != null && principal.hasRole(role);
    }

    @Override
    public boolean isSecure() {
        return secure;
    }

    @Override
    public String getAuthenticationScheme() {
        return "JWT";
    }

    
    public boolean hasPermission(String permissionCode) {
        return principal != null && principal.hasPermission(permissionCode);
    }

    public boolean hasAnyPermission(String... permissions) {
        return principal != null && principal.hasAnyPermission(permissions);
    }

    public boolean isAuthenticated() {
        return principal != null;
    }
}

