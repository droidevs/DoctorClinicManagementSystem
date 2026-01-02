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

    private final JwtPrincipal principale;
    private final boolean secure;
    
    public JwtSecurityContext(JwtPrincipal principale, boolean secure) {
        this.principale = principale;
        this.secure = secure;
    }
    @Override
    public Principal getUserPrincipal() {
       return principale;
    }

    @Override
    public boolean isUserInRole(String role) {
        return principale != null && principale.hasRole(role);
    }

    @Override
    public boolean isSecure() {
        return secure;
    }

    @Override
    public String getAuthenticationScheme() {
        return "JWT";
    }
    
}
