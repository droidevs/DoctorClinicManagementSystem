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
@Constraint(validatedBy = {})
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@NotNull(message = "Reason is required")
@Size(min = 5, max = 500, message = "Reason must be 5-500 characters")
@Pattern(
    regexp = "^[a-zA-Z0-9\\s\\.,;:'\"!?\\-\\/\\(\\)]+$",
    message = "Reason contains invalid characters"
)
public @interface ValidReason {
    String message() default "Invalid reason";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
