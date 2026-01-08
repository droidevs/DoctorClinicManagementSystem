/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package Dtos;

import java.time.Instant;
import java.util.Set;
import java.util.UUID;

public record RoleDto(
        UUID id,
        String name,
        Set<PermissionDto> permissions,
        // Audit fields (same order as AppointmentDto)
        UserDto createdBy,
        Instant createdAt,
        UserDto updatedBy,
        Instant updatedAt
) {}
