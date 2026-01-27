/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Repositories;

import Entities.PrescriptionEntity;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PrescriptionRepository {

    PrescriptionEntity save(PrescriptionEntity prescription);

    PrescriptionEntity update(PrescriptionEntity prescription);

    Optional<PrescriptionEntity> findById(UUID id);

    List<PrescriptionEntity> findByAppointmentId(UUID appointmentId);

    List<PrescriptionEntity> findAll();

    List<PrescriptionEntity> findAll(int page, int size);

    void softDelete(UUID id);

    void restore(UUID id);

    List<PrescriptionEntity> findByPatientId(UUID patientId);

    void delete(PrescriptionEntity prescription);
}
