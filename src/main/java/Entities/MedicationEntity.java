/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

import Listeners.AuditListener;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(
        name = "medications",
        indexes = {
                @Index(name = "idx_medication_drug_code", columnList = "drug_code"),
                @Index(name = "idx_medication_generic_name", columnList = "generic_name"),
                @Index(name = "idx_medication_category", columnList = "category_id")
        }
)
@EntityListeners(AuditListener.class)
public class MedicationEntity extends BaseEntity {

    /* ===== IDENTIFIERS ===== */

    @Column(name = "drug_code", nullable = false, unique = true, length = 50)
    private String drugCode;  // RxNorm, NDC, etc.

    @Column(name = "generic_name", nullable = false, length = 150)
    private String genericName;

    @Column(name = "brand_name", length = 150)
    private String brandName;

    @Column(name = "drug_class", length = 100)
    private String drugClass;

    /* ===== CATEGORY ===== */

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "category_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_medication_category")
    )
    private MedicineCategoryEntity category;

    /* ===== AVAILABLE FORMS ===== */

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(
            name = "medication_forms",
            joinColumns = @JoinColumn(
                    name = "medication_id",
                    foreignKey = @ForeignKey(name = "fk_medication_form_medication")
            )
    )
    @Column(name = "available_form", nullable = false, length = 50)
    private Set<String> availableForms = new HashSet<>();

    /* ===== MEDICAL DATA ===== */

    @Column(name = "contraindications", length = 2000)
    private String contraindications;
}
