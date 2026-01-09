/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;


import Listeners.AuditListener;
import jakarta.persistence.*;

import java.time.LocalTime;
import java.util.UUID;

import lombok.*;
import org.hibernate.annotations.Formula;

@Entity
@Table(
    name = "time_slot",
    uniqueConstraints = {
        @UniqueConstraint(
            name = "uk_time_slot_day_start",
            columnNames = {"day_schedule_id", "start_time"}
        )
    },
    indexes = {
        @Index(name = "idx_time_slot_day", columnList = "day_schedule_id"),
        @Index(name = "idx_time_slot_start_time", columnList = "start_time")
    }
)
@EntityListeners(AuditListener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TimeSlotEntity extends BaseEntity {

    /* ===== RELATION ===== */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "day_schedule_id", nullable = false)
    private DayScheduleEntity daySchedule;

    /* ===== TIMING ===== */
    @Column(name = "start_time", nullable = false)
    private LocalTime startTime;

    @Column(name = "end_time", nullable = false)
    private LocalTime endTime;

    /* ===== CAPACITY ===== */
    @Column(name = "max_reservations", nullable = false)
    private int maxReservations;

    @Formula("""
        (SELECT max_reservations - COALESCE(
            (SELECT COUNT(*) FROM appointments a 
             WHERE a.time_slot_id = id 
               AND a.status NOT IN ('CANCELLED', 'NO_SHOW')), 0)
        )
    """)
    @Setter(AccessLevel.NONE)
    private int availableReservations;
}

