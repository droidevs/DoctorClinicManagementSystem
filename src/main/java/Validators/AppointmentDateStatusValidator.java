/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Validators;

import Validators.annotations.ValidAppointmentDate;
import Requests.AppointmentFilterRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class AppointmentDateStatusValidator
        implements ConstraintValidator<ValidAppointmentDate, AppointmentFilterRequest> {

    @Override
    public boolean isValid(AppointmentFilterRequest request, ConstraintValidatorContext context) {

        if (request.date() == null || request.status() == null) {
            return true; // skip validation if either is null
        }

        LocalDate today = LocalDate.now();

        return switch (request.status()) {
            case "SCHEDULED" -> !request.date().isBefore(today);       // cannot be in past
            case "COMPLETED", "CANCELLED" -> !request.date().isAfter(today); // cannot be in future
            default -> true; // other values are validated by @Pattern
        };
    }
}

