package Entities.embeded;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PrescriptionSnapshot {

    /* ===== MEDICATION SNAPSHOT ===== */
    @Column(name = "medication_id", nullable = false)
    private UUID medicationId;

    @Column(name = "medication_name", nullable = false)
    private String medicationName;

    /* ===== DOSAGE SNAPSHOT ===== */
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "value", column = @Column(name = "dosage_value", precision = 10, scale = 4)),
            @AttributeOverride(name = "unit", column = @Column(name = "dosage_unit", length = 20)),
            @AttributeOverride(name = "route", column = @Column(name = "administration_route", length = 50)),
            @AttributeOverride(name = "form", column = @Column(name = "dosage_form", length = 50))
    })
    private Dosage dosage;

    /* ===== FREQUENCY SNAPSHOT ===== */
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "type", column = @Column(name = "frequency_type", length = 20)),
            @AttributeOverride(name = "timesPerPeriod", column = @Column(name = "times_per_period")),
            @AttributeOverride(name = "periodUnit", column = @Column(name = "period_unit", length = 20)),
            @AttributeOverride(name = "timingModifier", column = @Column(name = "timing_modifier", length = 30))
    })
    private Frequency frequency;

    /* ===== INSTRUCTIONS ===== */
    @Column(name = "instructions", length = 1000)
    private String instructions;

    @Column(name = "duration_days")
    private Integer durationDays;

    @Column(name = "refills_allowed")
    private Integer refillsAllowed;

    /* ===== SPECIFIC TIMES ===== */
    @ElementCollection
    @CollectionTable(
            name = "prescription_snapshot_specific_times",
            joinColumns = @JoinColumn(name = "prescription_snapshot_id")
    )
    @Column(name = "specific_time")
    private List<LocalTime> specificTimes = new ArrayList<>();
}
