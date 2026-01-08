/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositories.impl;


import Entities.AppointmentEntity;
import Enums.AppointmentStatus;
import Repositories.AppointmentRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
@Transactional
public class AppointmentRepositoryImpl
        implements AppointmentRepository {

    @PersistenceContext
    EntityManager em;

    /* -------------------------
     * Save / Update
     * ------------------------- */

    @Override
    public AppointmentEntity save(AppointmentEntity appointment) {
        em.persist(appointment);
        return appointment;
    }

    @Override
    public AppointmentEntity update(AppointmentEntity appointment) {
        return em.merge(appointment);
    }

    /* -------------------------
     * Finders
     * ------------------------- */

    @Override
    public Optional<AppointmentEntity> findById(UUID id) {
        return Optional.ofNullable(
                em.find(AppointmentEntity.class, id)
        );
    }

    @Override
    public List<AppointmentEntity> findAll() {
        return em.createQuery("""
                SELECT a
                FROM AppointmentEntity a
                ORDER BY a.appointmentDatetime DESC
                """, AppointmentEntity.class)
                .getResultList();
    }

    @Override
    public List<AppointmentEntity> findByDoctorId(UUID doctorId) {
        return em.createQuery("""
                SELECT a
                FROM AppointmentEntity a
                WHERE a.doctor.id = :doctorId
                ORDER BY a.appointmentDatetime DESC
                """, AppointmentEntity.class)
                .setParameter("doctorId", doctorId)
                .getResultList();
    }

    @Override
    public List<AppointmentEntity> findByPatientId(UUID patientId) {
        return em.createQuery("""
                SELECT a
                FROM AppointmentEntity a
                WHERE a.patient.id = :patientId
                ORDER BY a.appointmentDatetime DESC
                """, AppointmentEntity.class)
                .setParameter("patientId", patientId)
                .getResultList();
    }

    @Override
    public List<AppointmentEntity> findByDoctorIdAndStatus(
            UUID doctorId,
            AppointmentStatus status) {

        return em.createQuery("""
                SELECT a
                FROM AppointmentEntity a
                WHERE a.doctor.id = :doctorId
                  AND a.status = :status
                ORDER BY a.appointmentDatetime ASC
                """, AppointmentEntity.class)
                .setParameter("doctorId", doctorId)
                .setParameter("status", status)
                .getResultList();
    }

    /* -------------------------
     * Existence checks
     * ------------------------- */

    @Override
    public boolean existsByDoctorIdAndDatetime(
            UUID doctorId,
            LocalDateTime appointmentDatetime) {

        Long count = em.createQuery("""
                SELECT COUNT(a)
                FROM AppointmentEntity a
                WHERE a.doctor.id = :doctorId
                  AND a.appointmentDatetime = :datetime
                """, Long.class)
                .setParameter("doctorId", doctorId)
                .setParameter("datetime", appointmentDatetime)
                .getSingleResult();

        return count > 0;
    }

    /* -------------------------
     * Slot counting (business critical)
     * ------------------------- */

    @Override
    public long countByTimeSlotAndDate(UUID slotId, LocalDate date) {

        return em.createQuery("""
                SELECT COUNT(a)
                FROM AppointmentEntity a
                WHERE a.timeSlot.id = :slotId
                  AND DATE(a.appointmentDatetime) = :date
                """, Long.class)
                .setParameter("slotId", slotId)
                .setParameter("date", date)
                .getSingleResult();
    }

    @Override
    public long countByExceptionSlotAndDate(
            UUID exceptionSlotId,
            LocalDate date) {

        return em.createQuery("""
                SELECT COUNT(a)
                FROM AppointmentEntity a
                WHERE a.exceptionSlot.id = :exceptionSlotId
                  AND DATE(a.appointmentDatetime) = :date
                """, Long.class)
                .setParameter("exceptionSlotId", exceptionSlotId)
                .setParameter("date", date)
                .getSingleResult();
    }

    /* -------------------------
     * Delete
     * ------------------------- */

    @Override
    public void delete(AppointmentEntity appointment) {
        AppointmentEntity managed =
                em.contains(appointment)
                        ? appointment
                        : em.merge(appointment);

        em.remove(managed);
    }
}

