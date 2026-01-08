/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services.impl;


import Dtos.AppointmentDto;
import Entities.AppointmentEntity;
import Entities.PatientEntity;
import Entities.DoctorEntity;
import Entities.TimeSlotEntity;
import Exceptions.appointment.*;
import Exceptions.patient.PatientNotFoundException;
import Exceptions.doctor.DoctorNotFoundException;
import Exceptions.slot.TimeSlotFullyBookedException;
import Exceptions.slot.TimeSlotNotFoundException;
import Repositories.AppointmentRepository;
import Repositories.DoctorRepository;
import Repositories.PatientRepository;
import Repositories.TimeSlotRepository;
import Requests.*;
import Services.AppointmentService;
import jakarta.inject.Inject;

import jakarta.transaction.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Transactional
public class AppointmentServiceImpl implements AppointmentService {

    @Override
    public AppointmentDto create(CreateAppointmentRequest request) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public AppointmentDto update(String id, UpdateAppointmentRequest request) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public AppointmentDto addNotes(String id, AddAppointmentNoteRequest request) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public AppointmentDto findById(UUID id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<AppointmentDto> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<AppointmentDto> filter(AppointmentFilterRequest request) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public AppointmentDto updateStatus(UUID id, String status) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public AppointmentDto complete(String id, CompleteAppointmentRequest request) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public AppointmentDto cancel(String id, CancelAppointmentRequest request) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(UUID id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
}

