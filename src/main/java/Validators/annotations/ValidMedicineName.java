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
@NotNull(message = "Medicine name is required")
@Size(min = 2, max = 100, message = "Medicine name must be 2-100 characters")
@Pattern(
    regexp = "^[a-zA-Z0-9\\s\\-\\/\\+\\.\\(\\)\\[\\]\\{\\}]+$",
    message = "Medicine name contains invalid characters"
)
public @interface ValidMedicineName {
    String message() default "Invalid medicine name";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
