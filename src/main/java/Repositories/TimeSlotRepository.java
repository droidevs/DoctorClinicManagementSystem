/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Repositories;

import Entities.TimeSlotEntity;
import java.util.List;
import java.util.Optional;
import java.util.UUID;



public interface TimeSlotRepository {

    Optional<TimeSlotEntity> findById(UUID id);

    List<TimeSlotEntity> findByDaySchedule(UUID dayScheduleId);
}
