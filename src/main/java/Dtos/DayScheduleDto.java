/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package Dtos;

import Enums.Day;
import Enums.ReservationType;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

public record DayScheduleDto(
        UUID id,
        Day day,
        ReservationType reservationType,
        List<TimeSlotDto> slots,
        AuditDto audit
) {}

