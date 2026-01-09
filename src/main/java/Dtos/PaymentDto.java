package Dtos;

import Enums.PaymentMethod;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record PaymentDto(
        UUID id,

        BillDto bill,

        BigDecimal amount,
        PaymentMethod paymentMethod,

        UUID receivedBy,
        Instant receivedAt,

        AuditDto audit
) {}

