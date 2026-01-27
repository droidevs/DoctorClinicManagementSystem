/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

import Enums.Day;
import Enums.ReservationType;
import Listeners.AuditListener;
import jakarta.persistence.*;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(
    name = "day_schedule",
    uniqueConstraints = {
        @UniqueConstraint(name = "uk_day_schedule_weekday", columnNames = {"weekly_schedule_id", "day"})
    },
    indexes = {
        @Index(name = "idx_day_schedule_weekly_schedule", columnList = "weekly_schedule_id"),
        @Index(name = "idx_day_schedule_day", columnList = "day")
    }
)
@NamedQueries({
    @NamedQuery(name = "DaySchedule.findById", query = "SELECT d FROM DayScheduleEntity d WHERE d.id = :id"),
    @NamedQuery(name = "DaySchedule.findByWeeklyScheduleId", query = "SELECT d FROM DayScheduleEntity d WHERE d.weeklySchedule.id = :weeklyScheduleId ORDER BY d.day ASC"),
    @NamedQuery(name = "DaySchedule.findAll", query = "SELECT d FROM DayScheduleEntity d ORDER BY d.weeklySchedule.id, d.day ASC")
})
@EntityListeners(AuditListener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class DayScheduleEntity extends BaseEntity {

    /* ===== RELATION ===== */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "weekly_schedule_id", nullable = false)
    private WeeklyScheduleEntity weeklySchedule;

    /* ===== DAY OF WEEK ===== */
    @Enumerated(EnumType.STRING)
    @Column(name = "day", nullable = false, length = 10)
    private Day day;

    /* ===== RESERVATION TYPE ===== */
    @Enumerated(EnumType.STRING)
    @Column(name = "reservation_type", nullable = false, length = 20)
    private ReservationType reservationType;
}
