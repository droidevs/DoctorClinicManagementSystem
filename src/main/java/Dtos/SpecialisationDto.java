/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dtos;

import java.time.Instant;
import java.util.UUID;

/**
 *
 * @author admin
 */
public record SpecialisationDto(
        UUID id,
        String name,
        AuditDto audit // Contains createdBy, createdAt, updatedBy, updatedAt
) {}
