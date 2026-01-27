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
        @Index(name = "idx_specialisation_name", columnList = "name"),
        @Index(name = "idx_specialisations_deleted", columnList = "deleted")
    },
    uniqueConstraints = {
        @UniqueConstraint(name = "uk_specialisation_name", columnNames = "name")
    }
)
@NamedQueries({
    @NamedQuery(name = "Specialisation.findById", query = "SELECT s FROM SpecialisationEntity s WHERE s.id = :id"),
    @NamedQuery(name = "Specialisation.findByName", query = "SELECT s FROM SpecialisationEntity s WHERE s.name = :name AND s.deleted = false"),
    @NamedQuery(name = "Specialisation.findAll", query = "SELECT s FROM SpecialisationEntity s WHERE s.deleted = false ORDER BY s.createdAt DESC"),
    @NamedQuery(name = "Specialisation.findByNameLike", query = "SELECT s FROM SpecialisationEntity s WHERE LOWER(s.name) LIKE LOWER(:name) AND s.deleted = false ORDER BY s.createdAt DESC")
})
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

    @Column(name = "deleted", nullable = false)
    @Builder.Default
    private boolean deleted = false;
}
