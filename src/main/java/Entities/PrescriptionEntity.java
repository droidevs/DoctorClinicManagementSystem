/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
@Table(name = "prescriptions")
public class PrescriptionEntity extends BaseEntity {
    
    
    @ManyToOne(optional = false)
    private AppointmentEntity appointment;
    
    @Column(name = "medicine_name")
    private String medicineName;
    
    @Column(name = "dosage")
    private String dosage;
    
    @Column(name = "frequency")
    private String frequency;
    
    @Column(name = "instructions")
    private String instructions;
}
