/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Mappers;

import Dtos.AppointmentDto;
import Entities.AppointmentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(
        componentModel = "jakarta",
        uses = {
                AuditMapper.class,
                PatientMapper.class,
                DoctorMapper.class,
                TimeSlotMapper.class,
                ExceptionTimeSlotMapper.class
        }
)
public interface AppointmentMapper {

    /* ========================
       Entity → DTO
       ======================== */

    @Mapping(source = ".", target = "audit")
    AppointmentDto toDto(AppointmentEntity entity);


}

