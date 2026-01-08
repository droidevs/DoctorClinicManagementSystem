/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositories.impl;

import Entities.WeeklyScheduleEntity;
import Repositories.WeeklyScheduleRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

import java.util.Optional;
import java.util.UUID;


@Transactional
public class WeeklyScheduleRepositoryImpl implements WeeklyScheduleRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<WeeklyScheduleEntity> findActiveByDoctor(UUID doctorId) {
        String jpql = "SELECT w FROM WeeklyScheduleEntity w " +
                      "WHERE w.doctor.id = :doctorId AND w.active = true";
        TypedQuery<WeeklyScheduleEntity> query = entityManager.createQuery(jpql, WeeklyScheduleEntity.class);
        query.setParameter("doctorId", doctorId);
        return query.getResultStream().findFirst();
    }

    @Override
    public WeeklyScheduleEntity save(WeeklyScheduleEntity schedule) {
        entityManager.persist(schedule);
        return schedule;
    }
}

