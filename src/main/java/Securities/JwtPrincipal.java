/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Securities;

import java.security.Principal;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author admin
 */
public class JwtPrincipal implements Principal {

    private UUID userId;
    private String username;
    private List<String> roles;
    private String token;
    
     public JwtPrincipal(UUID userId, String username, List<String> roles, String token) {
        this.userId = userId;
        this.username = username;
        this.roles = roles;
        this.token = token;
    }
    
    @Override
    public String getName() {
        return userId.toString();  // Principal name is typically user ID
    }
    
    public UUID getUserId() { return userId; }
    public String getUsername() { return username; }
    public List<String> getRoles() { return roles; }
    public String getToken() { return token; }
    
    public boolean hasRole(String role) {
        return roles.contains(role);
    }
    
    public boolean hasAnyRole(String... requiredRoles) {
        for (String required : requiredRoles) {
            if (roles.contains(required)) {
                return true;
            }
        }
        return false;
    }
    
}
