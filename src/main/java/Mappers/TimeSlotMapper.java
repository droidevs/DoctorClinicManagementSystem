/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Mappers;

import Dtos.TimeSlotDto;
import Entities.TimeSlotEntity;
import Requests.CreateTimeSlotRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


import org.mapstruct.*;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(
        componentModel = "jakarta",
        uses = { AuditMapper.class }
)
public interface TimeSlotMapper {

    // ------------------------
    // Entity → DTO
    // ------------------------

    @Mapping(source = "daySchedule.id", target = "dayScheduleId")
    @Mapping(source = ".", target = "audit")
    TimeSlotDto toDto(TimeSlotEntity entity);

    // ------------------------
    // DTO → Entity
    // ------------------------

    @Mapping(target = "daySchedule", ignore = true)
    @Mapping(target = "availableReservations", ignore = true) // @Formula
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    TimeSlotEntity toEntity(TimeSlotDto dto);

    // ------------------------
    // Update existing entity
    // ------------------------

    @Mapping(target = "daySchedule", ignore = true)
    @Mapping(target = "availableReservations", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateEntityFromDto(
            TimeSlotDto dto,
            @MappingTarget TimeSlotEntity entity
    );
}
