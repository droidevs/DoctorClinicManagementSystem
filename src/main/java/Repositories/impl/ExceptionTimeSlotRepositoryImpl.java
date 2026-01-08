/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositories.impl;


import Entities.ExceptionTimeSlotEntity;
import Repositories.ExceptionTimeSlotRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
@Transactional
public class ExceptionTimeSlotRepositoryImpl
        implements ExceptionTimeSlotRepository {

    @PersistenceContext
    EntityManager em;

    /* --------------------------------
     * Find all time slots of an exception
     * -------------------------------- */

    @Override
    public List<ExceptionTimeSlotEntity> findByException(UUID exceptionId) {
        return em.createQuery("""
                SELECT ets
                FROM ExceptionTimeSlotEntity ets
                WHERE ets.exception.id = :exceptionId
                ORDER BY ets.startTime ASC
                """, ExceptionTimeSlotEntity.class)
                .setParameter("exceptionId", exceptionId)
                .getResultList();
    }
}

