/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Validators;

import Requests.CreateAppointmentRequest;
import Validators.annotations.ValidAppointmentSlots;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class AppointmentSlotValidator implements ConstraintValidator<ValidAppointmentSlots, CreateAppointmentRequest> {

    @Override
    public boolean isValid(CreateAppointmentRequest request, ConstraintValidatorContext context) {
        if (request == null) return true;

        boolean slotIdPresent = request.slotId() != null;
        boolean exceptionSlotIdPresent = request.exceptionSlotId() != null;

        // Exactly one must be present
        boolean valid = slotIdPresent ^ exceptionSlotIdPresent; // XOR operator

        if (!valid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                    "Appointment must have exactly one slot: either slotId or exceptionSlotId"
            ).addConstraintViolation();
        }

        return valid;
    }
}
