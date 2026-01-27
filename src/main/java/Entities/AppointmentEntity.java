/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

import Enums.AppointmentStatus;
import Listeners.AuditListener;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
    name = "appointments",
    indexes = {
        @Index(name = "idx_appointment_doctor", columnList = "doctor_id"),
        @Index(name = "idx_appointment_patient", columnList = "patient_id"),
        @Index(name = "idx_appointment_datetime", columnList = "appointment_datetime"),
        @Index(name = "idx_appointment_status", columnList = "status"),
        @Index(name = "idx_appointments_deleted", columnList = "deleted")
    }
)
@EntityListeners(AuditListener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class AppointmentEntity extends BaseEntity {

    /* ===== RELATIONS ===== */

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", nullable = false)
    private PatientEntity patient;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id", nullable = false)
    private DoctorEntity doctor;

    /* ===== TIME ===== */

    @Column(name = "appointment_date", nullable = false)
    private LocalDate appointmentDate;

    /* ===== NORMAL SLOT ===== */

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "time_slot_id")
    private TimeSlotEntity slot;

    /* ===== EXCEPTION SLOT ===== */

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exception_slot_id")
    private ExceptionTimeSlotEntity exceptionSlot;

    /* ===== STATUS ===== */

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 30)
    private AppointmentStatus status;

    /* ===== REASONS ===== */

    @Column(name = "reason", length = 500)
    private String reason;

    @Column(name = "cancel_reason", length = 500)
    private String cancelReason;

    /* ===== VALIDATION ===== */

    @Override
    protected void validateOnPersist() {
        validateSlotConsistency();
    }

    @Override
    protected void validateOnUpdate() {
        validateSlotConsistency();
    }

    private void validateSlotConsistency() {
        if (slot != null && exceptionSlot != null) {
            throw new IllegalStateException(
                "Appointment cannot have both timeSlot and exceptionSlot"
            );
        }

        if (slot == null && exceptionSlot == null) {
            throw new IllegalStateException(
                "Appointment must have either timeSlot or exceptionSlot"
            );
        }
    }

    @Column(name = "deleted", nullable = false)
    @Builder.Default
    private boolean deleted = false;
}