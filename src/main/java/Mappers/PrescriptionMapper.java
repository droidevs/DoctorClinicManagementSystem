/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Mappers;

import Dtos.PrescriptionDto;
import Entities.PrescriptionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 *
 * @author admin
 */
@Mapper(uses = {AppointmentMapper.class})
public interface PrescriptionMapper {
    
    PrescriptionMapper INSTANCE = Mappers.getMapper(PrescriptionMapper.class);
    
    PrescriptionDto toDto(PrescriptionEntity prescription);
    PrescriptionEntity toEntity(PrescriptionDto dto);
}
