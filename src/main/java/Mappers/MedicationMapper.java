package Mappers;



import Dtos.MedicationDto;
import Entities.MedicationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(
        componentModel = "jakarta",
        uses = {AuditMapper.class, MedicineCategoryMapper.class}
)
public interface MedicationMapper {
    @Mapping(source = ".", target = "audit") // Maps BaseEntity audit fields to AuditDto
    MedicationDto toDto(MedicationEntity entity);

    // Optional: if you want DTO → Entity mapping (ignoring audit fields)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    MedicationEntity toEntity(MedicationDto dto);
}
