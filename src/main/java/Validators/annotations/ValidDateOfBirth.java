/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/AnnotationType.java to edit this template
 */
package Validators.annotations;


import Validators.DateOfBirthValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.constraints.NotNull;
import java.lang.annotation.*;
import java.time.LocalDate;
import java.time.Period;


@Documented
@Constraint(validatedBy = DateOfBirthValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@NotNull(message = "Date of birth is required")
public @interface ValidDateOfBirth {
    String message() default "Invalid date of birth";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    
    // Configuration
    int minAge() default 18;
    int maxAge() default 150;
    boolean allowFuture() default false;
    boolean allowPast1900() default false;
    
    // Custom messages
    String futureMessage() default "Date of birth cannot be in the future";
    String minAgeMessage() default "Age must be at least {minAge} years";
    String maxAgeMessage() default "Age cannot exceed {maxAge} years";
    String unrealisticMessage() default "Date of birth seems unrealistic";
}
