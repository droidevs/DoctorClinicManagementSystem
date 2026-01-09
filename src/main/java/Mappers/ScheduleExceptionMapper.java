package Mappers;



import Dtos.ScheduleExceptionDto;
import Entities.ScheduleExceptionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(
        componentModel = "jakarta",
        uses = {
                AuditMapper.class
        }
)
public interface ScheduleExceptionMapper {

    // ------------------------
    // Entity → DTO
    // ------------------------

    @Mapping(source = "doctor.id", target = "doctorId")
    @Mapping(source = ".", target = "audit")
    ScheduleExceptionDto toDto(ScheduleExceptionEntity entity);

}

