/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Mappers;

import Dtos.MedicineCategoryDto;
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


    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "medicines", ignore = true) // avoid mapping related entities
    void updateEntityFromDto(MedicineCategoryDto dto, @MappingTarget MedicineCategoryEntity entity);
}