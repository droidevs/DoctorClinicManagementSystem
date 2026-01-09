/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Mappers;

import Dtos.MedicationDto;
import Dtos.MedicineCategoryDto;
import Entities.MedicationEntity;
import Entities.MedicineCategoryEntity;
import org.mapstruct.Mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

@Mapper(componentModel = "jakarta", uses = {AuditMapper.class})
public interface MedicineCategoryMapper {


    @Mapping(source = ".", target = "audit") // Use BaseEntity → AuditDto
    MedicineCategoryDto toDto(MedicineCategoryEntity entity);

}