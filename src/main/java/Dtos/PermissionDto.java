/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package Dtos;

import java.time.Instant;
import java.util.UUID;

import java.util.UUID;

public record PermissionDto(
        UUID id,
        String code,
        AuditDto audit  // Reuse AuditDto for all audit info
) {}

