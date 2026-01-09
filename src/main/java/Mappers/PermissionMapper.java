/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Mappers;



import Dtos.PermissionDto;
import Dtos.RoleDto;
import Entities.PermissionEntity;
import java.util.Set;

import Entities.RoleEntity;
import jakarta.persistence.EntityListeners;
import org.mapstruct.*;

@Mapper(componentModel = "jakarta", uses = {AuditMapper.class})
public interface PermissionMapper {

    // ------------------------
    // Entity → DTO
    // ------------------------
    @Mapping(source = ".", target = "audit")
    // Map audit fields from BaseEntity
    PermissionDto toDto(PermissionEntity entity);
}