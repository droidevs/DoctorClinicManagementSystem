/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Validators;

import Enums.SlotCode;
import Requests.CreateTimeSlotRequest;
import Validators.annotations.ValidTimeSlotRequest;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.lang.annotation.*;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;


public class TimeSlotRequestValidator 
    implements ConstraintValidator<ValidTimeSlotRequest, CreateTimeSlotRequest> {
    
    @Override
    public boolean isValid(CreateTimeSlotRequest request, ConstraintValidatorContext context) {
        if (request == null) return true;
        
        boolean valid = true;
        
        // Business Rule 1: Start time must be before end time
        if (request.startTime() != null && request.endTime() != null) {
            if (!request.startTime().isBefore(request.endTime())) {
                addViolation(context, "Start time must be before end time", "startTime");
                valid = false;
            }
            
            // Business Rule 2: Minimum slot duration (e.g., 15 minutes)
            long minutesDuration = ChronoUnit.MINUTES.between(request.startTime(), request.endTime());
            if (minutesDuration < 15) {
                addViolation(context, "Time slot must be at least 15 minutes", "endTime");
                valid = false;
            }
            
            // Business Rule 3: Maximum slot duration (e.g., 4 hours)
            if (minutesDuration > 240) {
                addViolation(context, "Time slot cannot exceed 4 hours", "endTime");
                valid = false;
            }
            
            // Business Rule 6: Validate max reservations based on duration
            if (request.maxReservations() > 0) {
                long hours = ChronoUnit.HOURS.between(request.startTime(), request.endTime());
                long maxAllowed = Math.max(1, hours * 4); // Max 4 per hour
                if (request.maxReservations() > maxAllowed) {
                    addViolation(context, 
                        String.format("Max reservations cannot exceed %d for this slot duration", maxAllowed),
                        "maxReservations");
                    valid = false;
                }
            }
            
            // Business Rule 7: Validate slot times against operational hours
            if (!isWithinOperationalHours(request.startTime(), request.endTime())) {
                addViolation(context, 
                    "Time slot must be within operational hours (6:00 AM - 10:00 PM)", 
                    "startTime");
                valid = false;
            }
        }
        
        // Business Rule 8: Validate order index is positive
        if (request.orderIndex() < 0) {
            addViolation(context, "Order index cannot be negative", "orderIndex");
            valid = false;
        }
        
        // Business Rule 9: Slot code validation based on time
        if (request.slotCode() != null && request.startTime() != null) {
            if (!isValidSlotCodeForTime(request.slotCode(), request.startTime())) {
                addViolation(context, 
                    String.format("Slot code '%s' is not valid for this time", request.slotCode()),
                    "slotCode");
                valid = false;
            }
        }
        
        return valid;
    }
    
    private boolean isWithinOperationalHours(LocalTime start, LocalTime end) {
        LocalTime opening = LocalTime.of(6, 0);   // 6:00 AM
        LocalTime closing = LocalTime.of(22, 0);  // 10:00 PM
        
        return !start.isBefore(opening) && !end.isAfter(closing);
    }
    
    private boolean isValidSlotCodeForTime(SlotCode slotCode, LocalTime time) {
        // Example: Morning slots should have AM codes, evening slots PM codes
        if (slotCode.name().contains("AM") && time.isAfter(LocalTime.NOON)) {
            return false;
        }
        if (slotCode.name().contains("PM") && time.isBefore(LocalTime.NOON)) {
            return false;
        }
        return true;
    }
    
    private void addViolation(ConstraintValidatorContext context, String message, String property) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(message)
               .addPropertyNode(property)
               .addConstraintViolation();
    }
}
