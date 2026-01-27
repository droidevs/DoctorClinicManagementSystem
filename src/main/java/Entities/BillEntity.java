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
                @Index(name = "idx_bill_paid_at", columnList = "paid_at"),
                @Index(name = "idx_bills_deleted", columnList = "deleted")
        }
)
@NamedQueries({
    @NamedQuery(name = "Bill.findById", query = "SELECT b FROM BillEntity b WHERE b.id = :id"),
    @NamedQuery(name = "Bill.findByAppointmentId", query = "SELECT b FROM BillEntity b WHERE b.appointment.id = :appointmentId AND b.deleted = false"),
    @NamedQuery(name = "Bill.findAll", query = "SELECT b FROM BillEntity b WHERE b.deleted = false ORDER BY b.createdAt DESC"),
    @NamedQuery(name = "Bill.findByStatus", query = "SELECT b FROM BillEntity b WHERE b.status = :status AND b.deleted = false ORDER BY b.createdAt DESC"),
    @NamedQuery(name = "Bill.findUnpaid", query = "SELECT b FROM BillEntity b WHERE b.status != 'PAID' AND b.deleted = false ORDER BY b.createdAt DESC"),
    @NamedQuery(name = "Bill.filter", query = "SELECT b FROM BillEntity b WHERE b.deleted = false AND (:status = '' OR b.status = :status) AND (:patientId IS NULL OR b.appointment.patient.id = :patientId) AND (:doctorId IS NULL OR b.appointment.doctor.id = :doctorId) AND (:unpaidOnly = false OR b.status != 'PAID') AND (:fromDate IS NULL OR b.createdAt >= :fromDate) AND (:toDate IS NULL OR b.createdAt <= :toDate) AND (:minAmount IS NULL OR b.amount >= :minAmount) AND (:maxAmount IS NULL OR b.amount <= :maxAmount) ORDER BY b.createdAt DESC")
})
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

    @Override
    protected void validateOnPersist() {
        validatePaymentConsistency();
    }

    @Override
    protected void validateOnUpdate() {
        validatePaymentConsistency();
    }

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
