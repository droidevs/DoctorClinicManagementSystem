/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Mappers;

import Dtos.PatientDto;
import Entities.PatientEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;




import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "jakarta", uses = {AuditMapper.class, UserMapper.class})
public interface PatientMapper {

    // ------------------------
    // Entity → DTO
    // ------------------------
    @Mapping(source = ".", target = "audit") // Map all audit fields into AuditDto
    PatientDto toDto(PatientEntity entity);

}


