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
@NotNull(message = "Password is required")
@Size(min = 8, max = 128, message = "Password must be 8-128 characters")
@Pattern.List({
    @Pattern(regexp = ".*[A-Z].*", message = "Password must contain at least one uppercase letter"),
    @Pattern(regexp = ".*[a-z].*", message = "Password must contain at least one lowercase letter"),
    @Pattern(regexp = ".*\\d.*", message = "Password must contain at least one digit"),
    @Pattern(regexp = ".*[@#$%^&+=!].*", message = "Password must contain at least one special character")
})
public @interface ValidPassword {
    String message() default "Invalid password";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
