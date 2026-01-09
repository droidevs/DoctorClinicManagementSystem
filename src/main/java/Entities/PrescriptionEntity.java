/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

import Entities.embeded.Dosage;
import Entities.embeded.Frequency;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
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
@Table(name = "prescriptions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PrescriptionEntity extends BaseEntity {
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "appointment_id")
    private AppointmentEntity appointment;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "medication_id")
    private MedicationEntity medication;  // Drug database
    
    @Embedded
    private Dosage dosage;  // Structured dosage
    
    @Embedded
    private Frequency frequency;  // Structured frequency
    
    @Column(name = "instructions", length = 1000)
    private String instructions;
    
    @Column(name = "duration_days")
    private Integer durationDays;
    
    @Column(name = "refills_allowed")
    private Integer refillsAllowed;
    
    @OneToMany(mappedBy = "prescription", cascade = CascadeType.ALL)
    @Builder.Default
    private List<PrescriptionEditHistoryEntity> editHistory = new ArrayList<>();
}
