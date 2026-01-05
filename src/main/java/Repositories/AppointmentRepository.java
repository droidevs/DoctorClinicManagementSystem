/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Repositories;


import Entities.AppointmentEntity;
import Enums.AppointmentStatus;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AppointmentRepository {

    AppointmentEntity save(AppointmentEntity appointment);

    AppointmentEntity update(AppointmentEntity appointment);

    Optional<AppointmentEntity> findById(UUID id);

    List<AppointmentEntity> findAll();

    List<AppointmentEntity> findByDoctorId(UUID doctorId);

    List<AppointmentEntity> findByPatientId(UUID patientId);

    List<AppointmentEntity> findByDoctorIdAndStatus(
            UUID doctorId,
            AppointmentStatus status
    );

    boolean existsByDoctorIdAndDatetime(
            UUID doctorId,
            LocalDateTime appointmentDatetime
    );

    void delete(AppointmentEntity appointment);
    
    long countByTimeSlotAndDate(UUID slotId, LocalDate date);

    long countByExceptionSlotAndDate(UUID exceptionSlotId, LocalDate date);
}
