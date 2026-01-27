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
@NamedQueries({
    @NamedQuery(name = "Patient.findById", query = "SELECT p FROM PatientEntity p WHERE p.id = :id"),
    @NamedQuery(name = "Patient.findByUserId", query = "SELECT p FROM PatientEntity p WHERE p.user.id = :userId AND p.deleted = false"),
    @NamedQuery(name = "Patient.findAll", query = "SELECT p FROM PatientEntity p WHERE p.deleted = false ORDER BY p.createdAt DESC"),
    @NamedQuery(name = "Patient.findByName", query = "SELECT p FROM PatientEntity p WHERE (LOWER(p.firstName) LIKE LOWER(:name) OR LOWER(p.lastName) LIKE LOWER(:name)) AND p.deleted = false ORDER BY p.createdAt DESC"),
    @NamedQuery(name = "Patient.findByPhone", query = "SELECT p FROM PatientEntity p WHERE p.phone = :phone AND p.deleted = false"),
    @NamedQuery(name = "Patient.filter", query = "SELECT p FROM PatientEntity p WHERE p.deleted = false AND (:name = '%' OR LOWER(p.firstName) LIKE LOWER(:name) OR LOWER(p.lastName) LIKE LOWER(:name)) AND (:email = '' OR LOWER(p.user.email) = LOWER(:email)) ORDER BY p.createdAt DESC")
})
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

    /* ===== VALIDATION ===== */

    @Override
    protected void validateOnPersist() {
        validateDateOfBirth();
    }

    @Override
    protected void validateOnUpdate() {
        validateDateOfBirth();
    }

    private void validateDateOfBirth() {
        if (dateOfBirth != null && dateOfBirth.isAfter(java.time.LocalDate.now())) {
            throw new IllegalStateException("Date of birth cannot be in the future");
        }
    }
}
