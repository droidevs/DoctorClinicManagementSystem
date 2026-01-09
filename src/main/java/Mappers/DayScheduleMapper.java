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


}
