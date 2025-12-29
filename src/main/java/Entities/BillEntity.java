/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

import Enums.BillStatus;
import Enums.PaymentMethod;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
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
@Table(name = "bills")
public class BillEntity extends BaseEntity {
   
    @OneToOne(optional = false)
    private AppointmentEntity appointment;
    
    @Column(name = "amount")
    private BigDecimal amount;
    
    @Column(name = "status")
    private BillStatus status;
    
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;
    
    private LocalDateTime paidAt;
}
