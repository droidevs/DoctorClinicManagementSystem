package Mappers;


import Dtos.PrescriptionSnapshotDto;
import Entities.embeded.PrescriptionSnapshot;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        componentModel = "jakarta",
        uses = {DosageMapper.class, FrequencyMapper.class} // Use embedded mappers
)
public interface PrescriptionSnapshotMapper {

    // Entity → DTO
    @Mapping(source = "dosage", target = "dosage")       // Will use DosageMapper
    @Mapping(source = "frequency", target = "frequency") // Will use FrequencyMapper
    PrescriptionSnapshotDto toDto(PrescriptionSnapshot snapshot);

}

