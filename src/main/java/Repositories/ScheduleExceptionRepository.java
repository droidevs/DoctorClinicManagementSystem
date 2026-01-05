/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Repositories;

import Entities.ScheduleExceptionEntity;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

public interface ScheduleExceptionRepository {

    Optional<ScheduleExceptionEntity> findOneTime(UUID doctorId, LocalDate date);

    Optional<ScheduleExceptionEntity> findMonthly(UUID doctorId, int day);

    Optional<ScheduleExceptionEntity> findYearly(UUID doctorId, int month, int day);

    ScheduleExceptionEntity save(ScheduleExceptionEntity exception);
}

