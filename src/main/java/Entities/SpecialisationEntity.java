/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

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
    name = "specialisations",
    indexes = {
        @Index(name = "idx_specialisation_name", columnList = "name")
    },
    uniqueConstraints = {
        @UniqueConstraint(name = "uk_specialisation_name", columnNames = "name")
    }
)
@EntityListeners(AuditListener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class SpecialisationEntity extends BaseEntity {

    @Column(
        name = "name",
        nullable = false,
        length = 150
    )
    private String name;
}

