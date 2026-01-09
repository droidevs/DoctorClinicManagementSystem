/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

import Listeners.AuditListener;
import jakarta.persistence.*;

import java.time.LocalTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(
    name = "exception_time_slot",
    indexes = {
        @Index(name = "idx_exception_slot_exception", columnList = "exception_id"),
        @Index(name = "idx_exception_slot_start_time", columnList = "start_time")
    }
)
@EntityListeners(AuditListener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class ExceptionTimeSlotEntity extends BaseEntity {

    /* ===== RELATION ===== */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "exception_id", nullable = false)
    private ScheduleExceptionEntity exception;

    /* ===== TIME ===== */
    @Column(name = "start_time", nullable = false)
    private LocalTime startTime;

    @Column(name = "end_time", nullable = false)
    private LocalTime endTime;

    @Column(name = "max_reservations", nullable = false)
    private int maxReservations;

    /* ===== VALIDATION ===== */
    @PrePersist
    @PreUpdate
    private void validateTimes() {
        if (endTime.isBefore(startTime) || endTime.equals(startTime)) {
            throw new IllegalStateException("End time must be after start time");
        }
        if (maxReservations < 1) {
            throw new IllegalStateException("maxReservations must be at least 1");
        }
    }
}


