package Mappers;

import Dtos.AuditDto;
import Entities.BaseEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "jakarta")
public interface AuditMapper {

    @Mapping(source = "createdBy.id", target = "createdBy")
    @Mapping(source = "createdAt", target = "createdAt")
    @Mapping(source = "updatedBy.id", target = "updatedBy")
    @Mapping(source = "updatedAt", target = "updatedAt")
    AuditDto toDto(BaseEntity entity);
}
