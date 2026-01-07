/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/AnnotationType.java to edit this template
 */
package Validators.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {})
@NotNull(message = "Dosage value cannot be null")
@DecimalMin(value = "0.0", inclusive = false, message = "Dosage must be positive")
@Digits(integer = 6, fraction = 3, message = "Invalid dosage precision")
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidDosageValue {
    String message() default "Invalid dosage value";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
