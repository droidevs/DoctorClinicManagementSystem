/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package Loggings.business.context;

import Dtos.RoleDto;
import Enums.Role;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public record BusinessLogContext(
        UUID userId,
        Set<RoleDto> role,
        Map<String, Object> metadata
) {

    // Compact canonical constructor for safety
    public BusinessLogContext {
        metadata = metadata == null
                ? Collections.emptyMap()
                : Collections.unmodifiableMap(metadata);
    }

    /* --------------------------------------------------
     * Static factory methods (professional usage)
     * -------------------------------------------------- */

    /**
     * Full context (user + role + metadata)
     */
    public static BusinessLogContext of(
            UUID userId,
            RoleDto role,
            Map<String, Object> metadata
    ) {
        return new BusinessLogContext(userId, Set.of(role), metadata);
    }
    
    public static BusinessLogContext of(
            UUID userId,
            Set<RoleDto> roles,
            Map<String, Object> metadata
    ) {
        return new BusinessLogContext(userId, roles, metadata);
    }

    /**
     * Context without metadata
     */
    public static BusinessLogContext of(
            UUID userId,
            RoleDto role
    ) {
        return new BusinessLogContext(userId, Set.of(role), Map.of());
    }
    
    public static BusinessLogContext of(
            UUID userId,
            Set<RoleDto> roles
    ) {
        return new BusinessLogContext(userId, roles, Map.of());
    }

    /**
     * User context with a single metadata entry
     */
    public static BusinessLogContext of(
            UUID userId,
            RoleDto role,
            String key,
            Object value
    ) {
        return new BusinessLogContext(
                userId,
                Set.of(role),
                Map.of(key, value)
        );
    }
}

