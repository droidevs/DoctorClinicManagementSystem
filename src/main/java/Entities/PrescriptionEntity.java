/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

import Entities.embeded.Dosage;
import Entities.embeded.Frequency;
import Listeners.AuditListener;
import jakarta.persistence.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 *
 * @author admin
 */

@Entity
@Table(
        name = "prescriptions",
        indexes = {
                @Index(name = "idx_prescription_appointment", columnList = "appointment_id"),
                @Index(name = "idx_prescription_medication", columnList = "medication_id"),
                @Index(name = "idx_prescription_created_at", columnList = "created_at")
        }
)
@EntityListeners(AuditListener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class PrescriptionEntity extends BaseEntity {

    /* ===== RELATIONS ===== */

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "appointment_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_prescription_appointment")
    )
    private AppointmentEntity appointment;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "medication_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_prescription_medication")
    )
    private MedicationEntity medication;

    /* ===== EMBEDDED STRUCTURED DATA ===== */

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "value", column = @Column(name = "dosage_value", precision = 10, scale = 4)),
            @AttributeOverride(name = "unit", column = @Column(name = "dosage_unit", length = 20)),
            @AttributeOverride(name = "route", column = @Column(name = "administration_route", length = 50)),
            @AttributeOverride(name = "form", column = @Column(name = "dosage_form", length = 50))
    })
    private Dosage dosage;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "type", column = @Column(name = "frequency_type", length = 20)),
            @AttributeOverride(name = "timesPerPeriod", column = @Column(name = "frequency_times_per_period")),
            @AttributeOverride(name = "periodUnit", column = @Column(name = "frequency_period_unit", length = 20)),
            @AttributeOverride(name = "timingModifier", column = @Column(name = "frequency_timing_modifier", length = 30))
    })
    private Frequency frequency;

    /* ===== SPECIFIC TIMES COLLECTION ===== */
    @ElementCollection
    @CollectionTable(
            name = "prescription_specific_times",
            joinColumns = @JoinColumn(name = "prescription_id")
    )
    @Column(name = "specific_time")
    @Builder.Default
    private List<LocalTime> specificTimes = new ArrayList<>();

    /* ===== COLUMNS ===== */
    @Column(name = "instructions", length = 1000)
    private String instructions;

    @Column(name = "duration_days")
    private Integer durationDays;

    @Column(name = "refills_allowed")
    private Integer refillsAllowed;

    @Column(name = "deleted", nullable = false)
    @Builder.Default
    private boolean deleted = false;

    /* ===== EDIT HISTORY ===== */
    @OneToMany(
            mappedBy = "prescription",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    @Builder.Default
    private List<PrescriptionEditHistoryEntity> editHistory = new ArrayList<>();
}
