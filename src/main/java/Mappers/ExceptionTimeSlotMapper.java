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

@Mapper(componentModel = "jakarta", uses = {AuditMapper.class})
public interface ExceptionTimeSlotMapper {

    // Entity → DTO
    @Mappings({
            @Mapping(source = "exception.id", target = "exceptionId"),
            @Mapping(source = ".", target = "audit") // Map audit fields using AuditMapper
    })
    ExceptionTimeSlotDto toDto(ExceptionTimeSlotEntity entity);


}
