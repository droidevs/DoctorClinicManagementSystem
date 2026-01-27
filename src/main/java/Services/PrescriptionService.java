/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Services;

import Dtos.PrescriptionDto;
import Requests.CreatePrescriptionRequest;
import java.util.List;
import java.util.UUID;

public interface PrescriptionService {

    PrescriptionDto create(CreatePrescriptionRequest request);

    PrescriptionDto findById(UUID id);

    List<PrescriptionDto> findAll();

    List<PrescriptionDto> findAll(int page, int size);

    void softDelete(UUID id);

    void restore(UUID id);

    List<PrescriptionDto> searchByPatient(UUID patientId);
}
