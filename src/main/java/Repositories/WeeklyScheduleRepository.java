/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Repositories;

import Dtos.WeeklyScheduleDto;
import Entities.WeeklyScheduleEntity;
import java.util.Optional;
import java.util.UUID;

public interface WeeklyScheduleRepository {

    Optional<WeeklyScheduleEntity> findActiveByDoctor(UUID doctorId);

    WeeklyScheduleEntity save(WeeklyScheduleEntity schedule);
}
