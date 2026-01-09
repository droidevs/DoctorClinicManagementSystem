package Mappers;

import Dtos.SecretaryDto;
import Entities.SecretaryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {AuditMapper.class, UserMapper.class})
public interface SecretaryMapper {

    // ------------------------
    // Entity → DTO
    // ------------------------
    @Mapping(source = ".", target = "audit") // map all BaseEntity audit fields
    SecretaryDto toDto(SecretaryEntity entity);

    // ------------------------
    // DTO → Entity (ignore audit)
    // ------------------------
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    SecretaryEntity toEntity(SecretaryDto dto);

    // ------------------------
    // Update existing entity from DTO (ignore audit)
    // ------------------------
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateEntityFromDto(SecretaryDto dto, @MappingTarget SecretaryEntity entity);
}

