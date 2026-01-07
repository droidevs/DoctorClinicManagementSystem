/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/AnnotationType.java to edit this template
 */
package Validators.annotations;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {}) // Composition only
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@NotNull(message = "Amount is required")
@Positive(message = "Amount must be positive") // Using existing constraint!
@DecimalMin(value = "0.01", message = "Amount must be at least 0.01")
@DecimalMax(value = "1000000.00", message = "Amount cannot exceed 1,000,000")
@Digits(integer = 7, fraction = 2, message = "Amount must have up to 7 digits before decimal and 2 after")
public @interface ValidMonetaryAmount {
    String message() default "Invalid monetary amount";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}