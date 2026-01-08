/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositories.impl;


import Entities.TimeSlotEntity;
import Repositories.TimeSlotRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Transactional
public class TimeSlotRepositoryImpl implements TimeSlotRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<TimeSlotEntity> findById(UUID id) {
        TimeSlotEntity timeSlot = entityManager.find(TimeSlotEntity.class, id);
        return Optional.ofNullable(timeSlot);
    }

    @Override
    public List<TimeSlotEntity> findByDaySchedule(UUID dayScheduleId) {
        String jpql = "SELECT t FROM TimeSlotEntity t WHERE t.daySchedule.id = :dayScheduleId ORDER BY t.orderIndex";
        TypedQuery<TimeSlotEntity> query = entityManager.createQuery(jpql, TimeSlotEntity.class);
        query.setParameter("dayScheduleId", dayScheduleId);
        return query.getResultList();
    }
}
