package Dtos;

import java.time.Instant;
import java.time.LocalTime;
import java.util.UUID;

public record ExceptionTimeSlotDto(
        UUID id,
        UUID exceptionId,
        LocalTime startTime,
        LocalTime endTime,
        int maxReservations,

        // Audit fields grouped as AuditDto
        AuditDto audit
) {}

