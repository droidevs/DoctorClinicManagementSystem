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
@Min(value = 0, message = "Order index cannot be negative")
@Max(value = 100, message = "Order index cannot exceed 100")
public @interface ValidOrderIndex {
    String message() default "Invalid order index";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}