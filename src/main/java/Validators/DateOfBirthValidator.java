/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Validators;

import Validators.annotations.ValidDateOfBirth;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.Period;


public class DateOfBirthValidator implements ConstraintValidator<ValidDateOfBirth, LocalDate> {
    
    private int minAge;
    private int maxAge;
    private boolean allowFuture;
    private boolean allowPast1900;
    private String futureMessage;
    private String minAgeMessage;
    private String maxAgeMessage;
    private String unrealisticMessage;
    
    @Override
    public void initialize(ValidDateOfBirth constraint) {
        this.minAge = constraint.minAge();
        this.maxAge = constraint.maxAge();
        this.allowFuture = constraint.allowFuture();
        this.allowPast1900 = constraint.allowPast1900();
        this.futureMessage = constraint.futureMessage();
        this.minAgeMessage = constraint.minAgeMessage();
        this.maxAgeMessage = constraint.maxAgeMessage();
        this.unrealisticMessage = constraint.unrealisticMessage();
    }
    
    @Override
    public boolean isValid(LocalDate dateOfBirth, ConstraintValidatorContext context) {
        if (dateOfBirth == null) {
            return true; // @NotNull handles this
        }
        
        LocalDate today = LocalDate.now();
        
        // Check future date
        if (!allowFuture && dateOfBirth.isAfter(today)) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(futureMessage)
                   .addConstraintViolation();
            return false;
        }
        
        // Calculate age
        int age = Period.between(dateOfBirth, today).getYears();
        
        // Check if date is too far in future (for newborns)
        if (allowFuture && dateOfBirth.isAfter(today.plusDays(1))) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                "Date of birth cannot be more than 1 day in the future")
                   .addConstraintViolation();
            return false;
        }
        
        // Adjust calculation if future date is allowed
        if (allowFuture && dateOfBirth.isAfter(today)) {
            age = -1; // Newborn, age is effectively 0
        }
        
        // Check minimum age (skip for newborns)
        if (age >= 0 && age < minAge) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                minAgeMessage.replace("{minAge}", String.valueOf(minAge)))
                   .addConstraintViolation();
            return false;
        }
        
        // Check maximum age
        if (age > maxAge) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                maxAgeMessage.replace("{maxAge}", String.valueOf(maxAge)))
                   .addConstraintViolation();
            return false;
        }
        
        // Check unrealistic dates (born before 1900)
        if (!allowPast1900 && dateOfBirth.getYear() < 1900) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(unrealisticMessage)
                   .addConstraintViolation();
            return false;
        }
        
        return true;
    }
}
