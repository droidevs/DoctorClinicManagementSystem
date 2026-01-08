/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositories.impl;


import Entities.DayScheduleEntity;
import Enums.Day;
import Repositories.DayScheduleRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
@Transactional
public class DayScheduleRepositoryImpl
        implements DayScheduleRepository {

    @PersistenceContext
    EntityManager em;

    /* --------------------------------
     * Find by weekly schedule + day
     * -------------------------------- */

    @Override
    public Optional<DayScheduleEntity> findByWeeklyScheduleAndDay(
            UUID weeklyScheduleId,
            Day day) {

        TypedQuery<DayScheduleEntity> query =
                em.createQuery("""
                    SELECT d
                    FROM DayScheduleEntity d
                    WHERE d.weeklySchedule.id = :weeklyScheduleId
                      AND d.day = :day
                """, DayScheduleEntity.class);

        return query
                .setParameter("weeklyScheduleId", weeklyScheduleId)
                .setParameter("day", day)
                .getResultStream()
                .findFirst();
    }

    /* --------------------------------
     * Find all days of a weekly schedule
     * -------------------------------- */

    @Override
    public List<DayScheduleEntity> findByWeeklySchedule(UUID weeklyScheduleId) {
        return em.createQuery("""
                SELECT d
                FROM DayScheduleEntity d
                WHERE d.weeklySchedule.id = :weeklyScheduleId
                ORDER BY d.day ASC
                """, DayScheduleEntity.class)
                .setParameter("weeklyScheduleId", weeklyScheduleId)
                .getResultList();
    }
}

