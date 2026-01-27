/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package Requests;

import Enums.AppointmentStatus;
import Validators.annotations.ValidReason;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 *
 * @author admin
 */
public record UpdateAppointmentRequest(
        @NotNull(message = "Appointment datetime is required")
        @FutureOrPresent(message = "Appointment datetime cannot be in the past")
        LocalDateTime appointmentDate,

        @NotNull(message = "Status is required")
        AppointmentStatus status,

        @ValidReason
        String reason
) {}
