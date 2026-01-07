/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package Requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.UUID;

public record AddAppointmentNoteRequest(

        //@NotNull(message = "Appointment ID is required")
        //UUID appointmentId, // Uncomment if you want to include appointmentId

        @NotBlank(message = "Notes cannot be blank")
        @Size(max = 1000, message = "Notes cannot exceed 1000 characters")
        String notes

) {}

