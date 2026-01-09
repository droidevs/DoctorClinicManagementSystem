/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package Dtos;

import Requests.DosageRequest;
import Requests.FrequencyRequest;
import java.time.Instant;
import java.util.List;
import java.util.UUID;


import java.time.Instant;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

public record PrescriptionDto(
        UUID id,
        UUID appointmentId,
        MedicationDto medication,
        DosageDto dosage,
        FrequencyDto frequency,
        String instructions,
        Integer durationDays,
        Integer refillsAllowed,
        List<PrescriptionEditDto> editHistory,

        // Audit grouped in AuditDto
        AuditDto audit
) {}


