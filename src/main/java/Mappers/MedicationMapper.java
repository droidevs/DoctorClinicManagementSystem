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

}
