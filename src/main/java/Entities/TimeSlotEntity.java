/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.time.LocalTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

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
}

