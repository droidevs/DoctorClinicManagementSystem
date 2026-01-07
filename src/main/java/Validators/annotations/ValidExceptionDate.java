/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/AnnotationType.java to edit this template
 */
package Validators.annotations;

import Validators.ExceptionDateValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import java.lang.annotation.*;
import java.time.LocalDate;


@Documented
@Constraint(validatedBy = ExceptionDateValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@NotNull(message = "Exception date is required")
@Future(message = "Exception date must be in the future")
public @interface ValidExceptionDate {
    String message() default "Invalid exception date";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    
    // Configuration
    int minDays() default 1;      // Minimum days in future (default: tomorrow)
    int maxDays() default 365;    // Maximum days in future (default: 1 year)
    boolean allowToday() default false; // Whether to allow today's date
}