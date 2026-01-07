/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package Requests;

import Enums.ExceptionRecurrence;
import Enums.ExceptionType;
import Validators.annotations.ValidExceptionDate;
import Validators.annotations.ValidExceptionDay;
import Validators.annotations.ValidExceptionMonth;
import Validators.annotations.ValidReason;
import Validators.annotations.ValidScheduleExceptionRequest;
import Validators.annotations.ValidUUID;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.util.UUID;

@ValidScheduleExceptionRequest
public record CreateScheduleExceptionRequest(
    
    @ValidUUID
    UUID doctorId,
    
    @NotNull(message = "Exception type is required")
    ExceptionType exceptionType,
    
    @NotNull(message = "Recurrence is required")
    ExceptionRecurrence recurrence,
    
    // Conditional validations based on recurrence
    @ValidExceptionDate
    LocalDate exceptionDate,
    
    @ValidExceptionDay
    Integer exceptionDay,
    
    @ValidExceptionMonth
    Integer exceptionMonth,
    
    @ValidReason
    String reason
    
) {}