/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package Requests;

import Validators.annotations.ValidBillFilter;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.UUID;

@ValidBillFilter
public record BillFilterRequest(

        Pagination pagination,

        @Pattern(regexp = "PAID|UNPAID",
                 message = "Status must be PAID or UNPAID")
        String status,

        UUID patientId,
        UUID doctorId,

        Boolean unpaidOnly,

        // Dates in ISO format: "yyyy-MM-dd"
        @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "fromDate must be in yyyy-MM-dd format")
        String fromDate,

        @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "toDate must be in yyyy-MM-dd format")
        String toDate,

        @DecimalMin(value = "0.0", inclusive = true, message = "minAmount must be >= 0")
        Double minAmount,

        @DecimalMin(value = "0.0", inclusive = true, message = "maxAmount must be >= 0")
        Double maxAmount

) {
    // Builder method for convenience
    public static BillFilterRequest of(Pagination pagination, String status, UUID patientId, Boolean unpaidOnly) {
        return new BillFilterRequest(pagination, status, patientId, null, unpaidOnly, null, null, null, null);
    }

    // Optional: method to convert date strings to LocalDate
    public LocalDate getFromDateAsLocalDate() {
        return fromDate == null ? null : LocalDate.parse(fromDate);
    }

    public LocalDate getToDateAsLocalDate() {
        return toDate == null ? null : LocalDate.parse(toDate);
    }
}
