/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Repositories;

import Entities.SpecialisationEntity;
import Criteria.SpecialisationQuery;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SpecialisationRepository {

    SpecialisationEntity save(SpecialisationEntity specialisation);

    SpecialisationEntity update(SpecialisationEntity specialisation);

    Optional<SpecialisationEntity> findById(UUID id);

    Optional<SpecialisationEntity> findByName(String name);

    List<SpecialisationEntity> findAll();

    List<SpecialisationEntity> findAll(int page, int size);

    void softDelete(UUID id);

    void restore(UUID id);

    List<SpecialisationEntity> searchByName(String name);

    boolean existsByName(String name);

    void delete(SpecialisationEntity specialisation);

    List<SpecialisationEntity> filter(SpecialisationQuery query);
}
