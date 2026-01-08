/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Mappers;

import Dtos.TimeSlotDto;
import Entities.TimeSlotEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "jakarta", uses = {UserMapper.class})
public abstract class TimeSlotMapper {
    
    
    public abstract TimeSlotDto toDto(TimeSlotEntity entity);
    
    public abstract TimeSlotEntity toEntity(TimeSlotDto dto);

}