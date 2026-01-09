/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package Dtos;

import java.time.Instant;
import java.util.Set;
import java.util.UUID;

public record DoctorDto(
        UUID id,
        UserDto user,
        String firstName,
        String lastName,
        String gender,
        Set<SpecialisationDto> specialisations,
        AuditDto audit // contains createdBy, createdAt, updatedBy, updatedAt
) {}
