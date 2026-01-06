/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Securities;

import Dtos.PermissionDto;
import Dtos.RoleDto;
import java.security.Principal;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class JwtPrincipal implements Principal {

    private UUID userId;
    private String username;
    private Set<RoleDto> roles;
    private Set<String> permissionCodes;

    
    public JwtPrincipal(
            UUID userId,
            String username,
            Set<RoleDto> roles
    ) {
        this.userId = userId;
        this.username = username;
        this.roles = Set.copyOf(roles);

        this.permissionCodes = roles.stream()
                .flatMap(r -> r.permissions().stream())
                .map(PermissionDto::code)
                .collect(Collectors.toUnmodifiableSet());
    }

    /** Principal name = userId */
    @Override
    public String getName() {
        return userId.toString();
    }

    public UUID getUserId() {
        return userId;
    }

    public Set<RoleDto> getRoles() {
        return roles;
    }
    // ---------- Authorization ----------

    public boolean hasRole(String roleName) {
        return roles.stream()
                .anyMatch(r -> r.name().equals(roleName));
    }

    public boolean hasAnyRole(String... roleNames) {
        for (String role : roleNames) {
            if (hasRole(role)) return true;
        }
        return false;
    }

    public boolean hasPermission(String permissionCode) {
        return permissionCodes.contains(permissionCode);
    }

    public boolean hasAnyPermission(String... permissions) {
        for (String p : permissions) {
            if (permissionCodes.contains(p)) return true;
        }
        return false;
    }

    public boolean hasAllPermissions(String... permissions) {
        for (String p : permissions) {
            if (!permissionCodes.contains(p)) return false;
        }
        return true;
    }
}
