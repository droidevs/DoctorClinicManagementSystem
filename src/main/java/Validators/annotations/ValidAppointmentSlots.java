/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/AnnotationType.java to edit this template
 */
package Validators.annotations;

import Validators.AppointmentSlotValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AppointmentSlotValidator.class)
@Documented
public @interface ValidAppointmentSlots {
    String message() default "Either slotId or exceptionSlotId must be provided, not both null";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

