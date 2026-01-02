/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Services;

import Dtos.AppointmentDto;
import Requests.AddAppointmentNoteRequest;
import Requests.AppointmentFilterRequest;
import Requests.CancelAppointmentRequest;
import Requests.CompleteAppointmentRequest;
import Requests.CreateAppointmentRequest;
import Requests.UpdateAppointmentRequest;
import java.util.List;
import java.util.UUID;

public interface AppointmentService {

    AppointmentDto create(CreateAppointmentRequest request);
    
    AppointmentDto update(String id, UpdateAppointmentRequest request);
    
    AppointmentDto addNotes(String id, AddAppointmentNoteRequest request);

    AppointmentDto findById(UUID id);

    List<AppointmentDto> findAll();
    
    List<AppointmentDto> filter(AppointmentFilterRequest request);

    AppointmentDto updateStatus(UUID id, String status);
    
    AppointmentDto complete(String id, CompleteAppointmentRequest request);
    
    AppointmentDto cancel(String id, CancelAppointmentRequest request);

    void delete(UUID id);
}

