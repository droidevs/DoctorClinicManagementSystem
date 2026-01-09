/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

import Entities.embeded.PrescriptionSnapshot;
import Listeners.AuditListener;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(
        name = "prescription_edit_history",
        indexes = {
                @Index(name = "idx_presc_edit_prescription", columnList = "prescription_id"),
                @Index(name = "idx_presc_edit_created_at", columnList = "created_at"),
                @Index(name = "idx_presc_edit_notify", columnList = "requires_patient_notification")
        }
)
@EntityListeners(AuditListener.class)
public class PrescriptionEditHistoryEntity extends BaseEntity {

    /* ===== RELATION ===== */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "prescription_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_presc_edit_prescription")
    )
    private PrescriptionEntity prescription;

    /* ===== SNAPSHOTS ===== */
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "medicationId", column = @Column(name = "prev_medication_id")),
            @AttributeOverride(name = "medicationName", column = @Column(name = "prev_medication_name")),
            @AttributeOverride(name = "instructions", column = @Column(name = "prev_instructions")),
            @AttributeOverride(name = "durationDays", column = @Column(name = "prev_duration_days")),
            @AttributeOverride(name = "refillsAllowed", column = @Column(name = "prev_refills_allowed"))
            // Dosage and Frequency columns are mapped automatically
    })
    private PrescriptionSnapshot previousState;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "medicationId", column = @Column(name = "new_medication_id")),
            @AttributeOverride(name = "medicationName", column = @Column(name = "new_medication_name")),
            @AttributeOverride(name = "instructions", column = @Column(name = "new_instructions")),
            @AttributeOverride(name = "durationDays", column = @Column(name = "new_duration_days")),
            @AttributeOverride(name = "refillsAllowed", column = @Column(name = "new_refills_allowed"))
            // Dosage and Frequency columns are mapped automatically
    })
    private PrescriptionSnapshot newState;

    /* ===== METADATA ===== */
    @Column(name = "reason_for_change", length = 500)
    private String reasonForChange;

    @Column(name = "requires_patient_notification", nullable = false)
    private boolean requiresPatientNotification;
}

