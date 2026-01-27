/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Services;

import Dtos.PatientDto;
import Requests.CreatePatientRequest;
import Requests.UpdatePatientRequest;
import Requests.PatientFilterRequest;
import java.util.List;
import java.util.UUID;

public interface PatientService {

    PatientDto create(CreatePatientRequest request);

    PatientDto update(UUID id, UpdatePatientRequest request);

    PatientDto findById(UUID id);

    List<PatientDto> findAll();

    List<PatientDto> findAll(int page, int size);

    void delete(UUID id);

    void softDelete(UUID id);

    void restore(UUID id);

    List<PatientDto> searchByName(String name);

    boolean existsByEmail(String email);

    List<PatientDto> filter(PatientFilterRequest request);
}
