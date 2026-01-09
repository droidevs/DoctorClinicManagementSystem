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
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "jakarta", uses = {AuditMapper.class, UserMapper.class, SpecialisationMapper.class})
public interface DoctorMapper {

    // ------------------------
    // Entity → DTO
    // ------------------------
    @Mapping(source = ".", target = "audit") // Map BaseEntity audit fields to AuditDto
    DoctorDto toDto(DoctorEntity entity);


}


