/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

import Enums.PaymentMethod;
import Listeners.AuditListener;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
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
                @Index(name = "idx_payment_received_at", columnList = "received_at"),
                @Index(name = "idx_payments_deleted", columnList = "deleted")
        }
)
@NamedQueries({
    @NamedQuery(name = "Payment.findById", query = "SELECT p FROM PaymentEntity p WHERE p.id = :id"),
    @NamedQuery(name = "Payment.findByBillId", query = "SELECT p FROM PaymentEntity p WHERE p.bill.id = :billId"),
    @NamedQuery(name = "Payment.findAll", query = "SELECT p FROM PaymentEntity p ORDER BY p.createdAt DESC"),
    @NamedQuery(name = "Payment.findByReceivedBy", query = "SELECT p FROM PaymentEntity p WHERE p.receivedBy.id = :secretaryId ORDER BY p.createdAt DESC"),
    @NamedQuery(name = "Payment.existsByBillId", query = "SELECT COUNT(p) FROM PaymentEntity p WHERE p.bill.id = :billId")
})
@EntityListeners(AuditListener.class)
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

    @Override
    protected void validateOnPersist() {
        validatePayment();
    }

    @Override
    protected void validateOnUpdate() {
        validatePayment();
    }

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
