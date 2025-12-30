/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Repositories;


import Entities.AppointmentNoteEntity;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AppointmentNoteRepository {

    AppointmentNoteEntity save(AppointmentNoteEntity note);

    AppointmentNoteEntity update(AppointmentNoteEntity note);

    Optional<AppointmentNoteEntity> findById(UUID id);

    List<AppointmentNoteEntity> findByAppointmentId(UUID appointmentId);

    List<AppointmentNoteEntity> findByDoctorId(UUID doctorId);

    void delete(AppointmentNoteEntity note);
}
