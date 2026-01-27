/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

import Listeners.AuditListener;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

import lombok.*;
import lombok.experimental.SuperBuilder;


/**
 *
 * @author admin
 */
@EqualsAndHashCode(callSuper = true)
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
@NamedQueries({
    @NamedQuery(name = "Role.findById", query = "SELECT r FROM RoleEntity r WHERE r.id = :id"),
    @NamedQuery(name = "Role.findByName", query = "SELECT r FROM RoleEntity r WHERE r.name = :name"),
    @NamedQuery(name = "Role.findAll", query = "SELECT r FROM RoleEntity r ORDER BY r.name ASC")
})
@EntityListeners(AuditListener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
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
    @Builder.Default
    private Set<PermissionEntity> permissions = new HashSet<>();
}
