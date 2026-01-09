package Mappers;


import Dtos.DayScheduleDto;
import Entities.DayScheduleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(
        componentModel = "jakarta",
        uses = {
                AuditMapper.class,
                TimeSlotMapper.class
        }
)
public interface DayScheduleMapper {

    /* ========================
       Entity → DTO
       ======================== */

    @Mapping(source = ".", target = "audit")
    @Mapping(source = "timeSlots", target = "slots")
    DayScheduleDto toDto(DayScheduleEntity entity);

    /* ========================
       DTO → Entity
       ======================== */

    @Mapping(target = "weeklySchedule", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    DayScheduleEntity toEntity(DayScheduleDto dto);
}
