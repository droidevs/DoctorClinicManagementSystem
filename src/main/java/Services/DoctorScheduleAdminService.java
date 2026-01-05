/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Services;

import Dtos.DayScheduleDto;
import Dtos.TimeSlotDto;
import Dtos.WeeklyScheduleDto;
import Requests.CreateDayScheduleRequest;
import Requests.CreateTimeSlotRequest;
import Requests.CreateWeeklyScheduleRequest;

public interface DoctorScheduleAdminService {

    WeeklyScheduleDto createWeeklySchedule(CreateWeeklyScheduleRequest request);

    DayScheduleDto createDaySchedule(CreateDayScheduleRequest request);

    TimeSlotDto createTimeSlot(CreateTimeSlotRequest request);
}
