/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Securities;


import Dtos.RoleDto;
import Dtos.PermissionDto;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.SecurityContext;
import lombok.Getter;
import lombok.Setter;

import java.util.Collections;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;


@Getter
@Setter
@RequestScoped
public class RequestUserContext {

    @Inject
    private SecurityContext securityContext;

    private UUID userId;
    private Set<RoleDto> roles;
    private Set<String> permissionCodes;

    @PostConstruct
    void init() {

        if (securityContext == null ||
            securityContext.getUserPrincipal() == null) {

            this.userId = null;
            this.roles = Collections.emptySet();
            this.permissionCodes = Collections.emptySet();
            return;
        }

        this.userId = parseUserId(securityContext.getUserPrincipal().getName());

        if (userId == null) {
            this.roles = Collections.emptySet();
            this.permissionCodes = Collections.emptySet();
            return;
        }

        Object principalObj = securityContext.getUserPrincipal();
        if (principalObj instanceof JwtPrincipal jwtPrincipal) {
            this.roles = jwtPrincipal.getRoles();
            this.permissionCodes = roles.stream()
                    .flatMap(r -> r.permissions().stream())
                    .map(PermissionDto::code)
                    .collect(Collectors.toUnmodifiableSet());
        } else {
            // fallback empty
            this.roles = Collections.emptySet();
            this.permissionCodes = Collections.emptySet();
        }
    }

    private UUID parseUserId(String principalName) {
        try {
            return UUID.fromString(principalName);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    // --------- API ---------

    public UUID userId() {
        return userId;
    }

    public Set<RoleDto> roles() {
        return roles;
    }

    public Set<String> permissionCodes() {
        return permissionCodes;
    }

    public boolean hasRole(String roleName) {
        return roles.stream().anyMatch(r -> r.name().equals(roleName));
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

    public boolean isAuthenticated() {
        return userId != null && !roles.isEmpty();
    }
}
