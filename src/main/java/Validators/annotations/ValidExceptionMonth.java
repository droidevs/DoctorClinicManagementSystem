/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/AnnotationType.java to edit this template
 */
package Validators.annotations;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {})
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Min(value = 1, message = "Exception month must be at least 1")
@Max(value = 12, message = "Exception month cannot exceed 12")
public @interface ValidExceptionMonth {
    String message() default "Invalid exception month";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}