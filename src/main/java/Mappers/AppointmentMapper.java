/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Mappers;

import Dtos.AppointmentDto;
import Entities.AppointmentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 *
 * @author admin
 */
@Mapper(uses = {PatientMapper.class, DoctorMapper.class, UserMapper.class})
public interface AppointmentMapper {
    
    AppointmentMapper INSTANCE = Mappers.getMapper(AppointmentMapper.class);
    
    AppointmentDto toDto(AppointmentEntity appointment);
    
    AppointmentEntity toEntity(AppointmentDto dto);
}
