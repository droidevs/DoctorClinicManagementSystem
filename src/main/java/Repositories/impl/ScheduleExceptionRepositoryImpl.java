/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositories.impl;


import Entities.ScheduleExceptionEntity;
import Enums.ExceptionRecurrence;
import Repositories.ScheduleExceptionRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Transactional
public class ScheduleExceptionRepositoryImpl implements ScheduleExceptionRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<ScheduleExceptionEntity> findOneTime(UUID doctorId, LocalDate date) {
        String jpql = "SELECT s FROM ScheduleExceptionEntity s " +
                      "WHERE s.doctor.id = :doctorId " +
                      "AND s.recurrence = :recurrence " +
                      "AND s.exceptionDate = :date";
        TypedQuery<ScheduleExceptionEntity> query = entityManager.createQuery(jpql, ScheduleExceptionEntity.class);
        query.setParameter("doctorId", doctorId);
        query.setParameter("recurrence", ExceptionRecurrence.ONE_TIME);
        query.setParameter("date", date);
        return query.getResultStream().findFirst();
    }

    @Override
    public Optional<ScheduleExceptionEntity> findMonthly(UUID doctorId, int day) {
        String jpql = "SELECT s FROM ScheduleExceptionEntity s " +
                      "WHERE s.doctor.id = :doctorId " +
                      "AND s.recurrence = :recurrence " +
                      "AND s.exceptionDay = :day";
        TypedQuery<ScheduleExceptionEntity> query = entityManager.createQuery(jpql, ScheduleExceptionEntity.class);
        query.setParameter("doctorId", doctorId);
        query.setParameter("recurrence", ExceptionRecurrence.MONTHLY);
        query.setParameter("day", day);
        return query.getResultStream().findFirst();
    }

    @Override
    public Optional<ScheduleExceptionEntity> findYearly(UUID doctorId, int month, int day) {
        String jpql = "SELECT s FROM ScheduleExceptionEntity s " +
                      "WHERE s.doctor.id = :doctorId " +
                      "AND s.recurrence = :recurrence " +
                      "AND s.exceptionMonth = :month " +
                      "AND s.exceptionDay = :day";
        TypedQuery<ScheduleExceptionEntity> query = entityManager.createQuery(jpql, ScheduleExceptionEntity.class);
        query.setParameter("doctorId", doctorId);
        query.setParameter("recurrence", ExceptionRecurrence.YEARLY);
        query.setParameter("month", month);
        query.setParameter("day", day);
        return query.getResultStream().findFirst();
    }

    @Override
    public ScheduleExceptionEntity save(ScheduleExceptionEntity exception) {
        entityManager.persist(exception);
        return exception;
    }
}

