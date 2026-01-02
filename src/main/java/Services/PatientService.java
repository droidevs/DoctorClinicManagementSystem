/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Services;

import Dtos.PatientDto;
import Requests.CreatePatientRequest;
import Requests.UpdatePatientRequest;
import java.util.List;
import java.util.UUID;

public interface PatientService {

    PatientDto create(CreatePatientRequest request);

    PatientDto update(UUID id, UpdatePatientRequest request);

    PatientDto findById(UUID id);

    List<PatientDto> findAll();

    void delete(UUID id);
}
