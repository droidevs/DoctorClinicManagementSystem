/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Mappers;

import Dtos.PatientDto;
import Entities.PatientEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;




@Mapper(uses = {UserMapper.class})
public interface PatientMapper {
    PatientMapper INSTANCE = Mappers.getMapper(PatientMapper.class);

    PatientDto toDto(PatientEntity patient);
    PatientEntity toEntity(PatientDto dto);
}

