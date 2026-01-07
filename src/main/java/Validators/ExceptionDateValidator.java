/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Validators;

import Validators.annotations.ValidExceptionDate;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.time.LocalDate;


// Validator implementation
public class ExceptionDateValidator 
    implements ConstraintValidator<ValidExceptionDate, LocalDate> {
    
    private int minDays;
    private int maxDays;
    private boolean allowToday;
    
    @Override
    public void initialize(ValidExceptionDate constraint) {
        this.minDays = constraint.minDays();
        this.maxDays = constraint.maxDays();
        this.allowToday = constraint.allowToday();
    }
    
    @Override
    public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
        // Null values are handled by @NotNull
        if (value == null) {
            return true;
        }
        
        LocalDate today = LocalDate.now();
        
        // Check if date is in the future (handled by @Future annotation)
        // But we need to adjust for "allowToday" option
        if (!allowToday && value.isEqual(today)) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                "Exception date must be after today")
                   .addConstraintViolation();
            return false;
        }
        
        // Calculate minimum allowed date
        LocalDate minDate = today.plusDays(minDays);
        
        // For "allowToday", adjust min date
        if (allowToday && minDays == 1) {
            // If allowing today and minDays=1, then today is valid
            minDate = today;
        }
        
        // Check minimum days in future
        if (value.isBefore(minDate)) {
            context.disableDefaultConstraintViolation();
            String message = allowToday && minDays == 1 ? 
                "Exception date must be today or in the future" :
                String.format("Exception date must be at least %d days in the future", minDays);
            context.buildConstraintViolationWithTemplate(message)
                   .addConstraintViolation();
            return false;
        }
        
        // Check maximum days in future
        LocalDate maxDate = today.plusDays(maxDays);
        if (value.isAfter(maxDate)) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                String.format("Exception date cannot be more than %d days in the future", maxDays))
                   .addConstraintViolation();
            return false;
        }
        
        return true;
    }
}