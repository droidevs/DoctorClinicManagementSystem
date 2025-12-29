/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

import Enums.Role;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author admin
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity {
    
    @Column(name = "username", nullable = false, unique = true)
    private String username;
    
    @Column(name = "email",nullable = false, unique = true)
    private String email;
    
    @Column(name = "password",nullable = false)
    private String passwordHash;
    
    @Enumerated(EnumType.STRING)
    private Role role;
    
    private boolean enabled = true;
}
