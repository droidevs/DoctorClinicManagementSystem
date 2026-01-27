/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

import Listeners.AuditListener;
import jakarta.persistence.*;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;


@Entity
@Table(
    name = "patients",
    indexes = {
        @Index(name = "idx_patient_user", columnList = "user_id"),
        @Index(name = "idx_patient_phone", columnList = "phone"),
        @Index(name = "idx_patients_deleted", columnList = "deleted")
    },
    uniqueConstraints = {
        @UniqueConstraint(name = "uk_patient_user", columnNames = "user_id")
    }
)
@EntityListeners(AuditListener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class PatientEntity extends BaseEntity {

    /* ===== IDENTITY ===== */

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
        name = "user_id",
        nullable = false,
        unique = true
    )
    private UserEntity user;

    /* ===== PERSONAL INFO ===== */

    @Column(name = "first_name", length = 50, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 50, nullable = false)
    private String lastName;

    @Column(name = "gender", length = 10)
    private String gender; // MALE / FEMALE / OTHER (can later become enum)

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    /* ===== CONTACT ===== */

    @Column(name = "phone", length = 30)
    private String phone;

    @Column(name = "address", length = 255)
    private String address;

    /* ===== SOFT DELETE ===== */

    @Column(name = "deleted", nullable = false)
    @Builder.Default
    private boolean deleted = false;
}
