/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Repositories;

import Entities.DayScheduleEntity;
import Enums.Day;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DayScheduleRepository {

    Optional<DayScheduleEntity> findByWeeklyScheduleAndDay(
            UUID weeklyScheduleId,
            Day day
    );

    List<DayScheduleEntity> findByWeeklySchedule(UUID weeklyScheduleId);
}
