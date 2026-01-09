package Dtos;

import java.time.Instant;
import java.util.UUID;

public record AuditDto(
        UUID createdBy,
        Instant createdAt,
        UUID updatedBy,
        Instant updatedAt
) {}
