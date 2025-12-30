/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Mappers;

import Dtos.DoctorDto;
import Dtos.SpecialisationDto;
import Entities.DoctorEntity;
import Entities.SpecialisationEntity;
import java.util.Set;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 *
 * @author admin
 */
@Mapper(uses = {UserMapper.class})
public interface DoctorMapper {
    
    DoctorMapper INSTANCE = Mappers.getMapper(DoctorMapper.class);
    
    
    DoctorDto toDto(DoctorEntity doctor);
    DoctorEntity toEntity(DoctorDto dto);

    SpecialisationDto toDto(SpecialisationEntity specialisation);
    SpecialisationEntity toEntity(SpecialisationDto dto);
    Set<SpecialisationDto> toDtoSet(Set<SpecialisationEntity> specialisations);
    Set<SpecialisationEntity> toEntitySet(Set<SpecialisationDto> dtos);
}

