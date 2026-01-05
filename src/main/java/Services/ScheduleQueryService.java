/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Services;

import Dtos.TimeSlotDto;
import Dtos.WeeklyScheduleDto;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface ScheduleQueryService {

    WeeklyScheduleDto getWeeklySchedule(UUID doctorId);

    List<TimeSlotDto> getAvailableSlots(UUID doctorId, LocalDate date);
}
