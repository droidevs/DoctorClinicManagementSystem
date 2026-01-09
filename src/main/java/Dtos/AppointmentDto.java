/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Dtos;


import Entities.ExceptionTimeSlotEntity;
import java.time.LocalDateTime;
import java.util.UUID;
import java.time.LocalDateTime;
import Enums.AppointmentStatus;
import java.time.Instant;
import java.time.LocalDate;

public record AppointmentDto(
        UUID id,

        PatientDto patient,
        DoctorDto doctor,

        LocalDate appointmentDate,

        TimeSlotDto slot,
        ExceptionTimeSlotDto exceptionSlot,

        AppointmentStatus status,
        String reason,

        AuditDto audit
) {}

