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

    /* ========================
       DTO → Entity
       ======================== */

    @Mapping(target = "patient", ignore = true)
    @Mapping(target = "doctor", ignore = true)
    @Mapping(target = "slot", ignore = true)
    @Mapping(target = "exceptionSlot", ignore = true)
    // audit (system-managed)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    AppointmentEntity toEntity(AppointmentDto dto);
}

