/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package Requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CancelAppointmentRequest(

        @NotBlank(message = "Reason for cancellation cannot be blank")
        @Size(max = 500, message = "Reason cannot exceed 500 characters")
        String reason

) {}
