/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Mappers;


import Dtos.TimeSlotDto;
import Entities.ExceptionTimeSlotEntity;
import org.mapstruct.*;
import java.util.List;

@Mapper(componentModel = "jakarta", uses = {UserMapper.class})
public interface ExceptionTimeSlotMapper {
    
    
    TimeSlotDto toDto(ExceptionTimeSlotEntity entity);
    
    
    ExceptionTimeSlotEntity toEntity(TimeSlotDto dto);
   
    
    // Collections
    List<TimeSlotDto> toDtoList(List<ExceptionTimeSlotEntity> entities);
    List<ExceptionTimeSlotEntity> toEntityList(List<TimeSlotDto> dtos);
}