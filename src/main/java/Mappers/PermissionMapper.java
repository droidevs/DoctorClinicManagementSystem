/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Mappers;



import Dtos.PermissionDto;
import Entities.PermissionEntity;
import java.util.Set;
import org.mapstruct.*;

@Mapper(componentModel = "jakarta" , uses = {UserMapper.class})
public interface PermissionMapper {
    
    PermissionDto toDto(PermissionEntity entity);
    
    PermissionEntity toEntity(PermissionDto dto);
    
    Set<PermissionDto> toDtoSet(Set<PermissionEntity> entities);
    
    Set<PermissionEntity> toEntitySet(Set<PermissionDto> dtos);
    
}
