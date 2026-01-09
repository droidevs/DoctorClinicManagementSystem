package Mappers;

import Dtos.SpecialisationDto;
import Entities.SpecialisationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {AuditMapper.class})
public interface SpecialisationMapper {

    // ------------------------
    // Entity → DTO
    // ------------------------
    @Mapping(source = ".", target = "audit") // Map all BaseEntity audit fields
    SpecialisationDto toDto(SpecialisationEntity entity);

    // ------------------------
    // DTO → Entity
    // ------------------------
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    SpecialisationEntity toEntity(SpecialisationDto dto);

    // ------------------------
    // Update existing entity from DTO (ignoring audit)
    // ------------------------
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateEntityFromDto(SpecialisationDto dto, @MappingTarget SpecialisationEntity entity);
}

