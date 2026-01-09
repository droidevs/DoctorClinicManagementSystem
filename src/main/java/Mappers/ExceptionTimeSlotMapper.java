/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Mappers;


import Dtos.ExceptionTimeSlotDto;
import Dtos.TimeSlotDto;
import Entities.ExceptionTimeSlotEntity;
import org.mapstruct.*;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = {AuditMapper.class})
public interface ExceptionTimeSlotMapper {

    // Entity → DTO
    @Mappings({
            @Mapping(source = "exception.id", target = "exceptionId"),
            @Mapping(source = ".", target = "audit") // Map audit fields using AuditMapper
    })
    ExceptionTimeSlotDto toDto(ExceptionTimeSlotEntity entity);

    // DTO → Entity
    @Mappings({
            @Mapping(source = "exceptionId", target = "exception.id"),
            @Mapping(target = "id", ignore = true),      // JPA-managed
            @Mapping(target = "createdBy", ignore = true),
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "updatedBy", ignore = true),
            @Mapping(target = "updatedAt", ignore = true)
    })
    ExceptionTimeSlotEntity toEntity(ExceptionTimeSlotDto dto);
}
