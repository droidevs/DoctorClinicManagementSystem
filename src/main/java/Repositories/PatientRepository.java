/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Repositories;

import Entities.PatientEntity;
import Criteria.PatientQuery;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PatientRepository {

    PatientEntity save(PatientEntity patient);

    PatientEntity update(PatientEntity patient);

    Optional<PatientEntity> findById(UUID id);

    Optional<PatientEntity> findByUserId(UUID userId);

    List<PatientEntity> findAll();

    List<PatientEntity> findAll(int page, int size);

    List<PatientEntity> findByName(String name);

    List<PatientEntity> findByPhone(String phone);

    void softDelete(UUID id);

    void restore(UUID id);

    List<PatientEntity> searchByName(String name);

    void delete(PatientEntity patient);

    List<PatientEntity> filter(PatientQuery query);
}
