/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import java.time.Instant;
import java.util.UUID;

import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 *
 * @author admin
 */
@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    protected UUID id;

    /* ===== AUDIT ===== */

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by", updatable = false)
    protected UserEntity createdBy;

    @Column(name = "created_at", nullable = false, updatable = false)
    protected Instant createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "updated_by")
    protected UserEntity updatedBy;

    @Column(name = "updated_at")
    protected Instant updatedAt;

    /* ===== LIFECYCLE ===== */

    @PrePersist
    protected void onCreate() {
        this.createdAt = Instant.now();
        this.validateOnPersist();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = Instant.now();
        this.validateOnUpdate();
    }

    /* ===== VALIDATION HOOKS ===== */

    protected void validateOnPersist() {
        // Default: no-op, subclasses override for persist-specific validation
    }

    protected void validateOnUpdate() {
        // Default: no-op, subclasses override for update-specific validation
    }
}
