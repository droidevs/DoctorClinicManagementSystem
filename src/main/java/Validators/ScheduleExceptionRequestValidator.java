/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Validators;

import Enums.ExceptionRecurrence;
import Enums.ExceptionType;
import Requests.CreateScheduleExceptionRequest;
import Validators.annotations.ValidScheduleExceptionRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.time.LocalDate;



public class ScheduleExceptionRequestValidator 
    implements ConstraintValidator<ValidScheduleExceptionRequest, CreateScheduleExceptionRequest> {
    
    @Override
    public boolean isValid(CreateScheduleExceptionRequest request, ConstraintValidatorContext context) {
        if (request == null) return true;
        
        boolean valid = true;
        
        // Business Rule 1: Validate based on recurrence type
        if (request.recurrence() != null) {
            switch (request.recurrence()) {
                case ONE_TIME:
                    if (request.exceptionDate() == null) {
                        addViolation(context, "Exception date is required for ONE_TIME recurrence", "exceptionDate");
                        valid = false;
                    }
                    // For ONE_TIME, day/month should be null
                    if (request.exceptionDay() != null || request.exceptionMonth() != null) {
                        addViolation(context, "Exception day/month should be null for ONE_TIME recurrence", "exceptionDay");
                        valid = false;
                    }
                    // Date should not be in the past
                    if (request.exceptionDate() != null && request.exceptionDate().isBefore(LocalDate.now())) {
                        addViolation(context, "Exception date cannot be in the past", "exceptionDate");
                        valid = false;
                    }
                    break;
                    
                case MONTHLY:
                    if (request.exceptionDay() == null) {
                        addViolation(context, "Exception day is required for MONTHLY recurrence", "exceptionDay");
                        valid = false;
                    }
                    // For MONTHLY, date and month should be null
                    if (request.exceptionDate() != null || request.exceptionMonth() != null) {
                        addViolation(context, "Exception date/month should be null for MONTHLY recurrence", "exceptionDate");
                        valid = false;
                    }
                    // Day must be 1-31
                    if (request.exceptionDay() != null && (request.exceptionDay() < 1 || request.exceptionDay() > 31)) {
                        addViolation(context, "Exception day must be between 1 and 31", "exceptionDay");
                        valid = false;
                    }
                    break;
                    
                case YEARLY:
                    if (request.exceptionDay() == null || request.exceptionMonth() == null) {
                        addViolation(context, "Exception day and month are required for YEARLY recurrence", "exceptionDay");
                        valid = false;
                    }
                    // For YEARLY, date should be null
                    if (request.exceptionDate() != null) {
                        addViolation(context, "Exception date should be null for YEARLY recurrence", "exceptionDate");
                        valid = false;
                    }
                    // Day must be 1-31
                    if (request.exceptionDay() != null && (request.exceptionDay() < 1 || request.exceptionDay() > 31)) {
                        addViolation(context, "Exception day must be between 1 and 31", "exceptionDay");
                        valid = false;
                    }
                    // Month must be 1-12
                    if (request.exceptionMonth() != null && (request.exceptionMonth() < 1 || request.exceptionMonth() > 12)) {
                        addViolation(context, "Exception month must be between 1 and 12", "exceptionMonth");
                        valid = false;
                    }
                    // Validate day-month combination (e.g., Feb 30 is invalid)
                    if (request.exceptionDay() != null && request.exceptionMonth() != null) {
                        if (!isValidDayMonthCombination(request.exceptionDay(), request.exceptionMonth())) {
                            addViolation(context, 
                                String.format("Invalid date: day %d is not valid for month %d", 
                                    request.exceptionDay(), request.exceptionMonth()), 
                                "exceptionDay");
                            valid = false;
                        }
                    }
                    break;
            }
        }
        
        // Business Rule 2: EMERGENCY exceptions must have a reason
        if (request.exceptionType() == ExceptionType.EMERGENCY && 
            (request.reason() == null || request.reason().trim().isEmpty())) {
            addViolation(context, "Reason is required for EMERGENCY exceptions", "reason");
            valid = false;
        }
        
        // Business Rule 3: CUSTOM_SLOTS validation
        if (request.exceptionType() == ExceptionType.CUSTOM_SLOTS) {
            if (request.recurrence() == ExceptionRecurrence.YEARLY) {
                addViolation(context, "CUSTOM_SLOTS cannot be YEARLY", "recurrence");
                valid = false;
            }
        }
        
        // Business Rule 4: Future date limits
        if (request.exceptionDate() != null && request.exceptionDate().isAfter(LocalDate.now().plusYears(1))) {
            addViolation(context, "Exception date cannot be more than 1 year in the future", "exceptionDate");
            valid = false;
        }
        
        return valid;
    }
    
    private boolean isValidDayMonthCombination(int day, int month) {
        try {
            // Try to create a date with the given day/month (using current year as placeholder)
            LocalDate.of(LocalDate.now().getYear(), month, day);
            return true;
        } catch (Exception e) {
            return false; // Invalid date (e.g., Feb 30, Apr 31)
        }
    }
    
    private void addViolation(ConstraintValidatorContext context, String message, String property) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(message)
               .addPropertyNode(property)
               .addConstraintViolation();
    }
}