/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package Dtos;

import java.time.Instant;
import java.util.Set;
import java.util.UUID;


public record MedicationDto(
        UUID id,
        String drugCode,
        String genericName,
        String brandName,
        String drugClass,
        MedicineCategoryDto category,
        Set<String> availableForms,
        String contraindications,
        boolean active,

        // Audit info
        AuditDto audit
) {}
