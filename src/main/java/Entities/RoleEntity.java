/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

import Listeners.AuditListener;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;


/**
 *
 * @author admin
 */
@Entity
@Table(
    name = "roles",
    indexes = {
        @Index(name = "idx_role_name", columnList = "name")
    },
    uniqueConstraints = {
        @UniqueConstraint(name = "uk_role_name", columnNames = "name")
    }
)
@EntityListeners(AuditListener.class)
@Getter
@Setter
public class RoleEntity extends BaseEntity {

    /* ===== ROLE NAME ===== */

    @Column(name = "name", nullable = false, length = 50)
    private String name; // ADMIN, DOCTOR, SECRETARY

    /* ===== PERMISSIONS ===== */

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "role_permissions",
        joinColumns = @JoinColumn(
            name = "role_id",
            nullable = false
        ),
        inverseJoinColumns = @JoinColumn(
            name = "permission_id",
            nullable = false
        )
    )
    private Set<PermissionEntity> permissions = new HashSet<>();
}

