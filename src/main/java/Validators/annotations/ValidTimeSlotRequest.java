/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/AnnotationType.java to edit this template
 */
package Validators.annotations;

import Validators.TimeSlotRequestValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.lang.annotation.*;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;


@Documented
@Constraint(validatedBy = TimeSlotRequestValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidTimeSlotRequest {
    String message() default "Invalid time slot request";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
