/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

import Enums.Role;
import Listeners.AuditListener;
import jakarta.persistence.*;
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

@Entity
@Table(
    name = "users",
    indexes = {
        @Index(name = "idx_user_username", columnList = "username"),
        @Index(name = "idx_user_email", columnList = "email"),
        @Index(name = "idx_user_role", columnList = "role_id"),
        @Index(name = "idx_users_deleted", columnList = "deleted")
    },
    uniqueConstraints = {
        @UniqueConstraint(name = "uk_user_username", columnNames = "username"),
        @UniqueConstraint(name = "uk_user_email", columnNames = "email")
    }
)
@NamedQueries({
    @NamedQuery(name = "User.findById", query = "SELECT u FROM UserEntity u WHERE u.id = :id"),
    @NamedQuery(name = "User.findByEmail", query = "SELECT u FROM UserEntity u WHERE u.email = :email"),
    @NamedQuery(name = "User.findByUsername", query = "SELECT u FROM UserEntity u WHERE u.username = :username"),
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM UserEntity u WHERE u.deleted = false ORDER BY u.createdAt DESC"),
    @NamedQuery(name = "User.findAllActive", query = "SELECT u FROM UserEntity u WHERE u.enabled = true AND u.deleted = false ORDER BY u.createdAt DESC"),
    @NamedQuery(name = "User.findByRole", query = "SELECT u FROM UserEntity u WHERE u.role.name = :roleName AND u.deleted = false ORDER BY u.createdAt DESC")
})
@EntityListeners(AuditListener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class UserEntity extends BaseEntity {

    @Column(name = "username", nullable = false, length = 100)
    private String username;

    @Column(name = "email", nullable = false, length = 150)
    private String email;

    @Column(name = "password_hash", nullable = false, length = 255)
    private String passwordHash;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "role_id", nullable = false)
    private RoleEntity role;

    @Column(name = "enabled", nullable = false)
    @Builder.Default
    private boolean enabled = true;

    @Column(name = "deleted", nullable = false)
    @Builder.Default
    private boolean deleted = false;
}
