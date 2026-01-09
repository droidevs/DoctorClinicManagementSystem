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

    // ------------------------
    // DTO → Entity
    // ------------------------

    @Mapping(target = "doctor", ignore = true) // set in service layer
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    ScheduleExceptionEntity toEntity(ScheduleExceptionDto dto);

    // ------------------------
    // Update existing entity
    // ------------------------

    @Mapping(target = "doctor", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateEntityFromDto(
            ScheduleExceptionDto dto,
            @MappingTarget ScheduleExceptionEntity entity
    );
}

