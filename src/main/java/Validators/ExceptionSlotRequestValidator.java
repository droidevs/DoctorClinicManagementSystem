/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/AnnotationType.java to edit this template
 */
package Validators;

import Requests.CreateExceptionSlotRequest;
import Validators.annotations.ValidExceptionSlotRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.time.temporal.ChronoUnit;


public class ExceptionSlotRequestValidator 
    implements ConstraintValidator<ValidExceptionSlotRequest, CreateExceptionSlotRequest> {
    
    @Override
    public boolean isValid(CreateExceptionSlotRequest request, ConstraintValidatorContext context) {
        if (request == null || request.startTime() == null || request.endTime() == null) {
            return true; // Let @NotNull handle missing fields
        }

        boolean valid = true;
        long minutes = ChronoUnit.MINUTES.between(request.startTime(), request.endTime());

        // 1. Logic: Start vs End
        if (!request.startTime().isBefore(request.endTime())) {
            addError(context, "startTime", "Start time must be before end time");
            valid = false;
        }

        // 2. Logic: Duration bounds
        if (minutes < 15 || minutes > 480) {
            addError(context, "startTime", "Duration must be between 15m and 8h");
            valid = false;
        }

        // 3. Logic: Ratio between fields
        if (request.maxReservations() > (minutes / 15)) { 
            addError(context, "maxReservations", "Too many reservations for this duration");
            valid = false;
        }

        return valid;
    }

    private void addError(ConstraintValidatorContext context, String property, String message) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(message)
               .addPropertyNode(property)
               .addConstraintViolation();
    }
}
