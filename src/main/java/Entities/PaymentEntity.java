/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

import Enums.PaymentMethod;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(
    name = "payment",
    uniqueConstraints = @UniqueConstraint(columnNames = "bill_id")
)
public class PaymentEntity extends BaseEntity {

    @OneToOne(optional = false)
    @JoinColumn(name = "bill_id", nullable = false, unique = true)
    private BillEntity bill;

    @Column(nullable = false)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentMethod paymentMethod;

    @ManyToOne(optional = false)
    @JoinColumn(name = "received_by", nullable = false)
    private SecretaryEntity receivedBy;

    @Column(unique = true)
    private String stripePaymentIntentId;

    @Column(nullable = false)
    private Instant receivedAt;
}
