/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Securities;


import Enums.Role;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.SecurityContext;

import java.util.EnumSet;
import java.util.Set;
import java.util.UUID;

@RequestScoped
public class RequestUserContext {

    @Context
    private SecurityContext securityContext;

    private UUID userId;
    private Set<Role> roles;

    @PostConstruct
    void init() {

        if (securityContext == null ||
            securityContext.getUserPrincipal() == null) {

            this.userId = null;
            this.roles = EnumSet.of(Role.SYSTEM);
            return;
        }

        this.userId = parseUserId(
                securityContext.getUserPrincipal().getName()
        );

        this.roles = resolveRoles(securityContext);
    }

    private UUID parseUserId(String principalName) {
        try {
            return UUID.fromString(principalName);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    private Set<Role> resolveRoles(SecurityContext ctx) {

        EnumSet<Role> resolvedRoles = EnumSet.noneOf(Role.class);

        for (Role role : Role.values()) {
            if (role != Role.SYSTEM &&
                ctx.isUserInRole(role.name())) {
                resolvedRoles.add(role);
            }
        }

        return resolvedRoles.isEmpty()
                ? EnumSet.of(Role.SYSTEM)
                : resolvedRoles;
    }

    // --------- API ---------

    public UUID userId() {
        return userId;
    }

    public Set<Role> roles() {
        return roles;
    }

    public boolean hasRole(Role role) {
        return roles.contains(role);
    }

    public boolean isAuthenticated() {
        return userId != null && !roles.contains(Role.SYSTEM);
    }
}