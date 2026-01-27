/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package Requests;

import Validators.annotations.ValidAppointmentDate;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.UUID;


@ValidAppointmentDate
public record AppointmentFilterRequest(

        Pagination pagination,

        // Optional filter by doctor
        // @NotNull can be added if required
        UUID doctorId,

        // Optional filter by patient
        UUID patientId,

        // Filter by appointment date
        // @PastOrPresent // use if only past dates allowed
        @Nullable
        LocalDate date,

        // Filter by status (e.g., "SCHEDULED", "COMPLETED", "CANCELLED")
        @Pattern(regexp = "SCHEDULED|COMPLETED|CANCELLED",
                 message = "Status must be one of SCHEDULED, COMPLETED, or CANCELLED")
        @Nullable
        String status

) {}
