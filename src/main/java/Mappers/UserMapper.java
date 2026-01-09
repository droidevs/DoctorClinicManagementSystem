/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Mappers;

import Dtos.UserDto;
import Entities.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {AuditMapper.class, RoleMapper.class})
public interface UserMapper {

    // ------------------------
    // Entity → DTO
    // ------------------------
    @Mapping(source = ".", target = "audit") // Map all audit fields into AuditDto
    UserDto toDto(UserEntity entity);

    // ------------------------
    // DTO → Entity
    // ------------------------
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    UserEntity toEntity(UserDto dto);

    // ------------------------
    // Update existing entity from DTO (ignoring audit)
    // ------------------------
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateEntityFromDto(UserDto dto, @MappingTarget UserEntity entity);
}


