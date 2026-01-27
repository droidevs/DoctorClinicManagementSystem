/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Repositories;

import Entities.DoctorEntity;
import Requests.DoctorFilterRequest;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DoctorRepository {

    DoctorEntity save(DoctorEntity doctor);

    DoctorEntity update(DoctorEntity doctor);

    Optional<DoctorEntity> findById(UUID id);

    Optional<DoctorEntity> findByUserId(UUID userId);

    List<DoctorEntity> findAll();

    List<DoctorEntity> findAll(int page, int size);

    List<DoctorEntity> findBySpecialisationId(UUID specialisationId);

    void delete(DoctorEntity doctor);
    
    void deleteById(UUID doctorId);

    void softDelete(UUID id);

    void restore(UUID id);

    List<DoctorEntity> searchByName(String name);

    boolean existsByEmail(String email);

    void assignPatient(UUID doctorId, UUID patientId);

    List<DoctorEntity> filter(DoctorFilterRequest request);
}
