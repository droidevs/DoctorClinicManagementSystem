package Mappers;

import Dtos.SecretaryDto;
import Entities.SecretaryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "jakarta", uses = {AuditMapper.class, UserMapper.class})
public interface SecretaryMapper {

    // ------------------------
    // Entity → DTO
    // ------------------------
    @Mapping(source = ".", target = "audit") // map all BaseEntity audit fields
    SecretaryDto toDto(SecretaryEntity entity);

}

