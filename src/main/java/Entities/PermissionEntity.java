/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(
    name = "permissions",
    indexes = {
        @Index(name = "idx_permission_code", columnList = "code")
    },
    uniqueConstraints = {
        @UniqueConstraint(name = "uk_permission_code", columnNames = "code")
    }
)
@Getter
@Setter
public class PermissionEntity extends BaseEntity {

    @Column(name = "code", nullable = false, length = 100)
    private String code; // e.g. APPOINTMENT_CREATE, USER_READ
}


