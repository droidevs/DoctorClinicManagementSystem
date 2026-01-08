/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package Dtos;

import Enums.ReservationType;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

public record DayScheduleDto(
    UUID id,  // Added ID since it's an entity
    int dayOfWeek,
    ReservationType reservationType,
    List<TimeSlotDto> slots,
    
    // Audit fields (same order as AppointmentDto)
    UserDto createdBy,
    Instant createdAt,
    UserDto updatedBy,
    Instant updatedAt
) {}
