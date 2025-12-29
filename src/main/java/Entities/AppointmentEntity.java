/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

import Enums.AppointmentStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
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
@Table(name = "appointments")
public class AppointmentEntity extends BaseEntity {
    
    
    @ManyToOne(optional = false)
    private PatientEntity patient;
    
    @ManyToOne(optional = false)
    private DoctorEntity doctor;
    
    @Column(name = "date_time")
    private LocalDateTime appointmentDatetime;
    
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private AppointmentStatus status;
    
    private String reason;
}
