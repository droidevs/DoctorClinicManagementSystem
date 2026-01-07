/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Validators;


import Validators.annotations.QuarterHour;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.time.LocalTime;
import java.time.temporal.ChronoField;

public class QuarterHourValidator implements ConstraintValidator<QuarterHour, Object> {

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) {
            return true; // Use @NotNull to validate presence
        }

        int minutes;
        if (value instanceof LocalTime time) {
            minutes = time.getMinute();
        } else if (value instanceof java.time.LocalDateTime dateTime) {
            minutes = dateTime.getMinute();
        } else {
            // Throw exception if applied to unsupported types
            throw new IllegalArgumentException("@QuarterHour only supports LocalTime or LocalDateTime");
        }

        // Check if minutes are 0, 15, 30, or 45
        return minutes % 15 == 0;
    }
}
