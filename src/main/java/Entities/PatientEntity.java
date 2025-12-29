/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
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
@Table(name = "patients")
public class PatientEntity extends BaseEntity {
    
    @OneToOne(optional = false)
    private UserEntity user;
    
    @Column(name = "first_name", length = 30)
    private String firstName;
    
    @Column(name = "last_name", length = 30)
    private String lastName;
    
    @Column(name = "gender")
    private String gender;
    
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;
    
    @Column(name = "phone")
    private String phone;
    
    @Column(name = "address")
    private String address;
    
}
