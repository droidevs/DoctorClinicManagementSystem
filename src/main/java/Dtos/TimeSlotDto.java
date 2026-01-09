/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package Dtos;


import java.time.Instant;
import java.time.LocalTime;
import java.util.UUID;

public record TimeSlotDto(
        UUID id,

        UUID dayScheduleId,

        LocalTime startTime,
        LocalTime endTime,

        int maxReservations,
        int availableReservations,

        AuditDto audit
) {}

