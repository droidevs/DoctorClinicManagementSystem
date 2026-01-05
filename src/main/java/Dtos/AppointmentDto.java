/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Dtos;


import java.time.LocalDateTime;
import java.util.UUID;
import java.time.LocalDateTime;
import Enums.AppointmentStatus;
import java.time.Instant;

public record AppointmentDto(
    UUID id,
    PatientDto patient,
    DoctorDto doctor,
    LocalDateTime appointmentDatetime,
    TimeSlotDto slot,
    AppointmentStatus status,
    String reason,
    Instant createdAt
) {}
