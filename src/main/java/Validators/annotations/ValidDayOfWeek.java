/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/AnnotationType.java to edit this template
 */
package Validators.annotations;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {}) // Composition only
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@NotNull(message = "Day of week is required")
@Min(value = 1, message = "Day of week must be at least 1 (Monday)")
@Max(value = 7, message = "Day of week cannot exceed 7 (Sunday)")
public @interface ValidDayOfWeek {
    String message() default "Invalid day of week";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}