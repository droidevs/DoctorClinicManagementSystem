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
@NotNull(message = "Max reservations is required")
@Min(value = 1, message = "Max reservations must be at least 1")
@Max(value = 100, message = "Max reservations cannot exceed 100")
public @interface ValidMaxReservations {
    String message() default "Invalid max reservations value";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
