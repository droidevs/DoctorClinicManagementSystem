/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/AnnotationType.java to edit this template
 */
package Validators.annotations;

import Validators.BillFilterValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = BillFilterValidator.class)
@Documented
public @interface ValidBillFilter {
    String message() default "Invalid Bill Filter request";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

