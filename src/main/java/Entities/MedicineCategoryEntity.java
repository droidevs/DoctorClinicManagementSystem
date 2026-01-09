/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;


import Listeners.AuditListener;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(
        name = "medicine_categories",
        indexes = {
                @Index(name = "idx_medicine_category_code", columnList = "code"),
                @Index(name = "idx_medicine_category_active", columnList = "is_active")
        }
)
@EntityListeners(AuditListener.class)
public class MedicineCategoryEntity extends BaseEntity {

    /* ===== BUSINESS IDENTIFIER ===== */

    @Column(name = "code", nullable = false, unique = true, length = 50)
    private String code;  // ANALGESIC, ANTIBIOTIC

    @Column(name = "name", nullable = false, length = 100)
    private String name;  // Analgesics, Antibiotics

    @Column(name = "description", length = 500)
    private String description;

    /* ===== STATUS ===== */

    @Column(name = "is_active", nullable = false)
    @Builder.Default
    private boolean active = true;

    /* ===== RELATION ===== */

    @OneToMany(
            mappedBy = "category",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = false
    )
    private Set<MedicationEntity> medicines = new HashSet<>();
}
