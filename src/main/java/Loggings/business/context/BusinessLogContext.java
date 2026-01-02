/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package Loggings.business.context;

import java.util.Collections;
import java.util.Map;
import java.util.UUID;

public record BusinessLogContext(
        UUID userId,
        String role,
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
            String role,
            Map<String, Object> metadata
    ) {
        return new BusinessLogContext(userId, role, metadata);
    }

    /**
     * Context without metadata
     */
    public static BusinessLogContext of(
            UUID userId,
            String role
    ) {
        return new BusinessLogContext(userId, role, Map.of());
    }

    /**
     * System / anonymous action
     */
    public static BusinessLogContext system() {
        return new BusinessLogContext(null, "SYSTEM", Map.of());
    }

    /**
     * System action with metadata
     */
    public static BusinessLogContext system(
            Map<String, Object> metadata
    ) {
        return new BusinessLogContext(null, "SYSTEM", metadata);
    }

    /**
     * User context with a single metadata entry
     */
    public static BusinessLogContext of(
            UUID userId,
            String role,
            String key,
            Object value
    ) {
        return new BusinessLogContext(
                userId,
                role,
                Map.of(key, value)
        );
    }
}

