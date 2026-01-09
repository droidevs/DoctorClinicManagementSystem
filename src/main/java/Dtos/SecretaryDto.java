package Dtos;

import Enums.SecretaryStatus;

import java.time.Instant;
import java.util.UUID;

public record SecretaryDto(
        UUID id,
        UserDto user,
        SecretaryStatus status,

        boolean canReceivePayments,
        boolean canVerifyIdentity,
        boolean canMergeAccounts,

        AuditDto audit // contains createdBy, createdAt, updatedBy, updatedAt
) {}
