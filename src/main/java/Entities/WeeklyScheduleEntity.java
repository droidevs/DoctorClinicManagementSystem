/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

import Listeners.AuditListener;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(
    name = "weekly_schedule",
    indexes = {
        @Index(name = "idx_weekly_schedule_doctor", columnList = "doctor_id"),
        @Index(name = "idx_weekly_schedule_active", columnList = "active")
    },
    uniqueConstraints = {
        @UniqueConstraint(name = "uk_weekly_schedule_doctor_active", columnNames = "doctor_id")
    }
)
@NamedQueries({
    @NamedQuery(name = "WeeklySchedule.findById", query = "SELECT w FROM WeeklyScheduleEntity w WHERE w.id = :id"),
    @NamedQuery(name = "WeeklySchedule.findByDoctorId", query = "SELECT w FROM WeeklyScheduleEntity w WHERE w.doctor.id = :doctorId AND w.active = true"),
    @NamedQuery(name = "WeeklySchedule.findAll", query = "SELECT w FROM WeeklyScheduleEntity w ORDER BY w.doctor.id ASC"),
    @NamedQuery(name = "WeeklySchedule.findActive", query = "SELECT w FROM WeeklyScheduleEntity w WHERE w.active = true ORDER BY w.doctor.id ASC")
})
@EntityListeners(AuditListener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class WeeklyScheduleEntity extends BaseEntity {

    /* ===== RELATION ===== */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "doctor_id", nullable = false)
    private DoctorEntity doctor;

    /* ===== STATUS ===== */
    @Column(name = "active", nullable = false)
    @Builder.Default
    private boolean active = true;
}
