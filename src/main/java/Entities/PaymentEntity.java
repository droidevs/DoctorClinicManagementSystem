/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

import Enums.PaymentMethod;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(
        name = "payments",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_payment_bill",
                        columnNames = "bill_id"
                ),
                @UniqueConstraint(
                        name = "uk_payment_stripe_intent",
                        columnNames = "stripe_payment_intent_id"
                )
        },
        indexes = {
                @Index(name = "idx_payment_bill", columnList = "bill_id"),
                @Index(name = "idx_payment_method", columnList = "payment_method"),
                @Index(name = "idx_payment_received_by", columnList = "received_by"),
                @Index(name = "idx_payment_received_at", columnList = "received_at")
        }
)
public class PaymentEntity extends BaseEntity {

    /* ===== RELATION ===== */

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "bill_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_payment_bill")
    )
    private BillEntity bill;

    /* ===== AMOUNT ===== */

    @Column(
            name = "amount",
            nullable = false,
            precision = 10,
            scale = 2
    )
    private BigDecimal amount;

    /* ===== PAYMENT METHOD ===== */

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method", nullable = false, length = 30)
    private PaymentMethod paymentMethod;

    /* ===== RECEIVER ===== */

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "received_by",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_payment_received_by")
    )
    private SecretaryEntity receivedBy;

    /* ===== EXTERNAL PROVIDERS ===== */

    @Column(
            name = "stripe_payment_intent_id",
            length = 150,
            unique = true
    )
    private String stripePaymentIntentId;

    /* ===== TIME ===== */

    @Column(name = "received_at", nullable = false)
    private Instant receivedAt;

    /* ===== VALIDATION ===== */

    @PrePersist
    @PreUpdate
    private void validatePayment() {

        if (amount == null || amount.signum() <= 0) {
            throw new IllegalStateException("Payment amount must be positive");
        }

        if (paymentMethod == PaymentMethod.CARD && stripePaymentIntentId == null) {
            throw new IllegalStateException(
                    "Stripe payment intent ID is required for CARD payments"
            );
        }

        if (paymentMethod == PaymentMethod.CASH && stripePaymentIntentId != null) {
            throw new IllegalStateException(
                    "Stripe payment intent ID must be null for CASH payments"
            );
        }
    }
}

