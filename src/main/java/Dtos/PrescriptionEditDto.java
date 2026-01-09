/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package Dtos;

import java.time.Instant;
import java.util.UUID;


public record PrescriptionEditDto(
        UUID id,
        UUID prescriptionId,
        PrescriptionSnapshotDto previousState,  // Using snapshot DTO
        PrescriptionSnapshotDto newState,       // Using snapshot DTO
        String reasonForChange,
        boolean requiresPatientNotification,

        // Audit fields
        AuditDto audit
) {}
