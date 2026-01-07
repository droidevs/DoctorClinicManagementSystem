/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/AnnotationType.java to edit this template
 */
package Validators.annotations;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.NotNull;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {}) // Composition only
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@NotNull(message = "Time is required")
@QuarterHour
public @interface ValidTime {
    String message() default "Invalid time";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
