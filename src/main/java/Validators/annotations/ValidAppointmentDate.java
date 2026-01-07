/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/AnnotationType.java to edit this template
 */
package Validators.annotations;

import Validators.AppointmentDateStatusValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AppointmentDateStatusValidator.class)
@Documented
public @interface ValidAppointmentDate {
    String message() default "Invalid appointment date for the given status";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}