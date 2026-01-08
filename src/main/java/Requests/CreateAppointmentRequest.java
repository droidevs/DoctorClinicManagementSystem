/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package Requests;

import Validators.annotations.ValidAppointmentSlots;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@ValidAppointmentSlots
public record CreateAppointmentRequest(

        @NotNull(message = "Patient ID is required")
        UUID patientId,

        @NotNull(message = "Doctor ID is required")
        UUID doctorId,

        @NotNull(message = "Appointment datetime is required")
        @FutureOrPresent(message = "Appointment datetime cannot be in the past")
        LocalDate appointmentDate,

        UUID slotId,              // normal weekly slot
        UUID exceptionSlotId      // custom slot (optional)
) {}

