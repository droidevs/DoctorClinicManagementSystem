/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Services;

import Dtos.DoctorDto;
import Requests.AssignSpecialisationsRequest;
import Requests.CreateDoctorRequest;
import Requests.DoctorFilterRequest;
import Requests.UpdateDoctorRequest;
import java.util.List;
import java.util.UUID;

public interface DoctorService {

    DoctorDto create(CreateDoctorRequest request);

    DoctorDto update(UUID id, UpdateDoctorRequest request);

    DoctorDto findById(UUID id);

    List<DoctorDto> findAll();
    
    List<DoctorDto> findAll(int page, int size);

    DoctorDto assignSpecialisations(String id, AssignSpecialisationsRequest request);

    void delete(UUID id);

    void softDelete(UUID id);

    void restore(UUID id);

    List<DoctorDto> searchByName(String name);

    boolean existsByEmail(String email);

    void assignPatient(UUID doctorId, UUID patientId);

    List<DoctorDto> filter(DoctorFilterRequest request);
}
