package Dtos;

import Enums.*;

import java.util.UUID;


public record PrescriptionSnapshotDto(
        UUID medicationId,
        String medicationName,
        DosageDto dosage,
        FrequencyDto frequency,
        String instructions,
        Integer durationDays,
        Integer refillsAllowed
) {}


