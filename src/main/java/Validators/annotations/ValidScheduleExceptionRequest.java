/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/AnnotationType.java to edit this template
 */
package Validators.annotations;


import Validators.ScheduleExceptionRequestValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.lang.annotation.*;
import java.time.LocalDate;

@Documented
@Constraint(validatedBy = ScheduleExceptionRequestValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidScheduleExceptionRequest {
    String message() default "Invalid schedule exception request";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
