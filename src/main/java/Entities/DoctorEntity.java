/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author admin
 */
@Entity
@Table(
    name = "doctors",
    indexes = {
        @Index(name = "idx_doctor_user", columnList = "user_id"),
        @Index(name = "idx_doctor_name", columnList = "last_name, first_name")
    },
    uniqueConstraints = {
        @UniqueConstraint(name = "uk_doctor_user", columnNames = "user_id")
    }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DoctorEntity extends BaseEntity {

    /* ===== IDENTITY ===== */

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
        name = "user_id",
        nullable = false,
        unique = true
    )
    private UserEntity user;

    /* ===== PERSONAL INFO ===== */

    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    @Column(name = "gender", length = 10)
    private String gender; // MALE / FEMALE / OTHER

    /* ===== MEDICAL ===== */

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "doctor_specialisations",
        joinColumns = @JoinColumn(name = "doctor_id", nullable = false),
        inverseJoinColumns = @JoinColumn(name = "specialisation_id", nullable = false)
    )
    @Builder.Default
    private Set<SpecialisationEntity> specialisations = new HashSet<>();
}

