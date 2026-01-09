package Mappers;

import Dtos.SpecialisationDto;
import Entities.SpecialisationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "jakarta", uses = {AuditMapper.class})
public interface SpecialisationMapper {

    // ------------------------
    // Entity → DTO
    // ------------------------
    @Mapping(source = ".", target = "audit") // Map all BaseEntity audit fields
    SpecialisationDto toDto(SpecialisationEntity entity);

}

