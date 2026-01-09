/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package Dtos;

import java.time.Instant;
import java.util.UUID;


public record MedicineCategoryDto(
        UUID id,
        String code,
        String name,
        String description,
        boolean active,

        // Audit information
        AuditDto audit
) {}

