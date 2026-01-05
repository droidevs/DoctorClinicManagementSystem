/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package Dtos;

import java.util.List;
import java.util.UUID;

public record WeeklyScheduleDto(
        UUID id,
        UUID doctorId,
        List<DayScheduleDto> days
) {}

