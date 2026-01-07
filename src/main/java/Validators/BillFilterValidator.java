/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Validators;

import Requests.BillFilterRequest;
import Validators.annotations.ValidBillFilter;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class BillFilterValidator implements ConstraintValidator<ValidBillFilter, BillFilterRequest> {

    @Override
    public boolean isValid(BillFilterRequest request, ConstraintValidatorContext context) {
        if (request == null) return true;

        boolean valid = true;

        // 1️⃣ unpaidOnly requires status = UNPAID
        if (Boolean.TRUE.equals(request.unpaidOnly()) && !"UNPAID".equals(request.status())) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                    "If unpaidOnly is true, status must be UNPAID"
            ).addPropertyNode("status").addConstraintViolation();
            valid = false;
        }

        // 2️⃣ Date range validation
        LocalDate from = request.fromDate() == null ? null : LocalDate.parse(request.fromDate());
        LocalDate to = request.toDate() == null ? null : LocalDate.parse(request.toDate());

        if (from != null && to != null && from.isAfter(to)) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                    "fromDate cannot be after toDate"
            ).addPropertyNode("fromDate").addConstraintViolation();
            valid = false;
        }

        // 3️⃣ Amount range validation
        Double min = request.minAmount();
        Double max = request.maxAmount();
        if (min != null && max != null && min > max) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                    "minAmount cannot be greater than maxAmount"
            ).addPropertyNode("minAmount").addConstraintViolation();
            valid = false;
        }

        return valid;
    }
}

