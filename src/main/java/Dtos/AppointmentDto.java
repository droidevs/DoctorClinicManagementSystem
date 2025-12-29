/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Dtos;


import java.time.LocalDateTime;
import java.util.UUID;
import java.time.LocalDateTime;
import Enums.AppointmentStatus;

public record AppointmentDto(
    UUID id,
    PatientDto patient,
    DoctorDto doctor,
    LocalDateTime appointmentDatetime,
    AppointmentStatus status,
    String reason
) {}
