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
@EntityListeners(AuditListener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
