/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Mappers;


import Dtos.RoleDto;
import Entities.RoleEntity;
import org.mapstruct.*;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "jakarta", uses = {PermissionMapper.class, UserMapper.class})
public interface RoleMapper {
    
    
    RoleDto toDto(RoleEntity entity);
    
    RoleEntity toEntity(RoleDto dto);
    
    Set<RoleDto> toDtoSet(Set<RoleEntity> entities);
    
    Set<RoleEntity> toEntitySet(Set<RoleDto> dtos);
    
    
}