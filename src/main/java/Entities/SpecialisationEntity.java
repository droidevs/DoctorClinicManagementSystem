/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SpecialisationEntity extends BaseEntity {

    @Column(
        name = "name",
        nullable = false,
        length = 150
    )
    private String name;
}

