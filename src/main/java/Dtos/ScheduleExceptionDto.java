/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package Dtos;

import Enums.ExceptionRecurrence;
import Enums.ExceptionType;
import java.time.LocalDate;
import java.util.UUID;

public record ScheduleExceptionDto(
        UUID id,
        ExceptionType exceptionType,
        ExceptionRecurrence recurrence,
        LocalDate exceptionDate,
        Integer exceptionDay,
        Integer exceptionMonth,
        String reason
) {}

