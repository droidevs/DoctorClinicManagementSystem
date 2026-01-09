/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package Dtos;

import Enums.Role;
import java.time.Instant;
import java.util.UUID;

public record UserDto(
        UUID id,
        String email,
        RoleDto role,
        boolean enabled,
        AuditDto audit // contains createdBy, createdAt, updatedBy, updatedAt
) {}


