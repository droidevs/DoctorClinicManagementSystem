/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

import Enums.SecretaryStatus;
import Listeners.AuditListener;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(
        name = "secretaries",
        indexes = {
                @Index(name = "idx_secretary_user", columnList = "user_id"),
                @Index(name = "idx_secretary_status", columnList = "status")
        }
)
@EntityListeners(AuditListener.class)
public class SecretaryEntity extends BaseEntity {

    /* ===== RELATION ===== */

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "user_id",
            nullable = false,
            unique = true,
            foreignKey = @ForeignKey(name = "fk_secretary_user")
    )
    private UserEntity user;

    /* ===== STATUS ===== */

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 30)
    private SecretaryStatus status;

    /* ===== BUSINESS PERMISSIONS (NOT AUTH) ===== */

    @Column(name = "can_receive_payments", nullable = false)
    private boolean canReceivePayments;

    @Column(name = "can_verify_identity", nullable = false)
    private boolean canVerifyIdentity;
}

