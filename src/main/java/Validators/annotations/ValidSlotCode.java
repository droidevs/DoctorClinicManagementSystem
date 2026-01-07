/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/AnnotationType.java to edit this template
 */
package Validators.annotations;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {}) // Composition only
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@NotNull(message = "Slot code is required")
@Size(min = 3, max = 20, message = "Slot code must be 3-20 characters")
@Pattern(
    regexp = "^[A-Z0-9_-]+$",
    message = "Slot code can only contain uppercase letters, numbers, underscores, and hyphens"
)
public @interface ValidSlotCode {
    String message() default "Invalid slot code";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
