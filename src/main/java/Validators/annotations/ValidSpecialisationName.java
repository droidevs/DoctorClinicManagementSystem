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
@NotNull(message = "Specialisation name is required")
@Size(min = 3, max = 100, message = "Specialisation name must be 3-100 characters")
@Pattern(
    regexp = "^[a-zA-Z\\s&.,'()-]+$",
    message = "Specialisation name can only contain letters, spaces, and basic punctuation (&.,'()-)"
)
public @interface ValidSpecialisationName {
    String message() default "Invalid specialisation name";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
