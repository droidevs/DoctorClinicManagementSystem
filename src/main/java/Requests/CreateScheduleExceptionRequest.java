/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package Requests;

import Enums.ExceptionRecurrence;
import Enums.ExceptionType;
import java.time.LocalDate;
import java.util.UUID;

public record CreateScheduleExceptionRequest(
        UUID doctorId,
        ExceptionType exceptionType,
        ExceptionRecurrence recurrence,

        // ONE_TIME
        LocalDate exceptionDate,

        // MONTHLY / YEARLY
        Integer exceptionDay,
        Integer exceptionMonth,

        String reason
) {}
