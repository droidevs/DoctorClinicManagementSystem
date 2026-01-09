/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

import Listeners.AuditListener;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author admin
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(
    name = "appointment_notes",
    indexes = {
        @Index(name = "idx_note_appointment", columnList = "appointment_id"),
        @Index(name = "idx_note_doctor", columnList = "doctor_id"),
        @Index(name = "idx_note_created_at", columnList = "created_at")
    }
)
@EntityListeners(AuditListener.class)
public class AppointmentNoteEntity extends BaseEntity {

    /* ===== RELATIONS ===== */

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "appointment_id", nullable = false)
    private AppointmentEntity appointment;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id", nullable = false)
    private DoctorEntity doctor;

    /* ===== CONTENT ===== */

    @Lob
    @Column(name = "note", nullable = false)
    private String note;
}
