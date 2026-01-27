/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

import Enums.BillStatus;
import Listeners.AuditListener;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 *
 * @author admin
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(
        name = "bills",
        indexes = {
                @Index(name = "idx_bill_appointment", columnList = "appointment_id"),
                @Index(name = "idx_bill_status", columnList = "status"),
                @Index(name = "idx_bill_paid_at", columnList = "paid_at")
        }
)
@EntityListeners(AuditListener.class)
public class BillEntity extends BaseEntity {

    /* ===== RELATION ===== */

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "appointment_id",
            nullable = false,
            unique = true,
            foreignKey = @ForeignKey(name = "fk_bill_appointment")
    )
    private AppointmentEntity appointment;

    /* ===== MONEY ===== */

    @Column(
            name = "amount",
            nullable = false,
            precision = 10,
            scale = 2
    )
    private BigDecimal amount;

    /* ===== STATUS ===== */

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 30)
    private BillStatus status;

    /* ===== PAYMENT INFO ===== */

    @Column(name = "paid_at")
    private Instant paidAt;

    /* ===== DELETED ===== */

    @Column(name = "deleted", nullable = false)
    @Builder.Default
    private boolean deleted = false;

    /* ===== VALIDATION ===== */

    @PrePersist
    @PreUpdate
    private void validatePaymentConsistency() {
        if (status == BillStatus.PAID && paidAt == null) {
            throw new IllegalStateException(
                    "paidAt must be set when bill status is PAID"
            );
        }

        if (status != BillStatus.PAID && paidAt != null) {
            throw new IllegalStateException(
                    "paidAt must be null unless bill status is PAID"
            );
        }
    }
}
