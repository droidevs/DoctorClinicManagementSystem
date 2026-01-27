/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositories.impl;


import Criteria.AppointmentQuery;
import Entities.AppointmentEntity;
import Enums.AppointmentStatus;
import Repositories.AppointmentRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

import java.time.LocalDate;
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
        return em.createQuery("SELECT a FROM AppointmentEntity a WHERE a.deleted = false ORDER BY a.appointmentDate DESC", AppointmentEntity.class)
                .getResultList();
    }

    @Override
    public List<AppointmentEntity> findAll(int page, int size) {
        return em.createQuery("SELECT a FROM AppointmentEntity a WHERE a.deleted = false ORDER BY a.appointmentDate DESC", AppointmentEntity.class)
            .setFirstResult(page * size)
            .setMaxResults(size)
            .getResultList();
    }

    @Override
    public List<AppointmentEntity> findByDoctorId(UUID doctorId) {
        return em.createQuery("SELECT a FROM AppointmentEntity a WHERE a.doctor.id = :doctorId AND a.deleted = false ORDER BY a.appointmentDate DESC", AppointmentEntity.class)
                .setParameter("doctorId", doctorId)
                .getResultList();
    }

    @Override
    public List<AppointmentEntity> findByPatientId(UUID patientId) {
        return em.createQuery("SELECT a FROM AppointmentEntity a WHERE a.patient.id = :patientId AND a.deleted = false ORDER BY a.appointmentDate DESC", AppointmentEntity.class)
                .setParameter("patientId", patientId)
                .getResultList();
    }

    @Override
    public List<AppointmentEntity> findByDoctorIdAndStatus(UUID doctorId, AppointmentStatus status) {
        return em.createQuery("SELECT a FROM AppointmentEntity a WHERE a.doctor.id = :doctorId AND a.status = :status AND a.deleted = false ORDER BY a.appointmentDate ASC", AppointmentEntity.class)
                .setParameter("doctorId", doctorId)
                .setParameter("status", status)
                .getResultList();
    }

    @Override
    public List<AppointmentEntity> filter(AppointmentQuery query) {
        StringBuilder jpql = new StringBuilder("SELECT a FROM AppointmentEntity a WHERE a.deleted = false");
        if (query.getDoctorId() != null) {
            jpql.append(" AND a.doctor.id = :doctorId");
        }
        if (query.getPatientId() != null) {
            jpql.append(" AND a.patient.id = :patientId");
        }
        if (query.getDate() != null) {
            jpql.append(" AND a.appointmentDate = :date");
        }
        if (query.getStatus() != null) {
            jpql.append(" AND a.status = :status");
        }
        jpql.append(" ORDER BY a.appointmentDate DESC");
        TypedQuery<AppointmentEntity> q = em.createQuery(jpql.toString(), AppointmentEntity.class);
        if (query.getDoctorId() != null) {
            q.setParameter("doctorId", query.getDoctorId());
        }
        if (query.getPatientId() != null) {
            q.setParameter("patientId", query.getPatientId());
        }
        if (query.getDate() != null) {
            q.setParameter("date", query.getDate());
        }
        if (query.getStatus() != null) {
            q.setParameter("status", query.getStatus());
        }
        if (query.getPagination() != null) {
            q.setFirstResult(query.getPagination().page() * query.getPagination().size());
            q.setMaxResults(query.getPagination().size());
        }
        return q.getResultList();
    }

    /* -------------------------
     * Existence checks
     * ------------------------- */

    @Override
    public boolean existsByDoctorIdAndDate(
            UUID doctorId,
            LocalDate appointmentDate) {

        Long count = em.createQuery("""
                SELECT COUNT(a)
                FROM AppointmentEntity a
                WHERE a.doctor.id = :doctorId
                  AND a.appointmentDate = :date
                  AND a.deleted = false
                """, Long.class)
                .setParameter("doctorId", doctorId)
                .setParameter("date", appointmentDate)
                .getSingleResult();

        return count > 0;
    }

    /* -------------------------
     * Slot counting (business critical)
     * ------------------------- */

    @Override
    public long countByTimeSlotAndDate(UUID slotId, LocalDate date) {
        return em.createQuery("SELECT COUNT(a) FROM AppointmentEntity a WHERE a.slot.id = :slotId AND a.appointmentDate = :date AND a.deleted = false", Long.class)
            .setParameter("slotId", slotId)
            .setParameter("date", date)
            .getSingleResult();
    }

    @Override
    public long countByExceptionSlotAndDate(UUID exceptionSlotId, LocalDate date) {
        return em.createQuery("SELECT COUNT(a) FROM AppointmentEntity a WHERE a.exceptionSlot.id = :exceptionSlotId AND a.appointmentDate = :date AND a.deleted = false", Long.class)
            .setParameter("exceptionSlotId", exceptionSlotId)
            .setParameter("date", date)
            .getSingleResult();
    }

    @Override
    public long countByDoctor(UUID doctorId) {
        return em.createQuery("SELECT COUNT(a) FROM AppointmentEntity a WHERE a.doctor.id = :doctorId AND a.deleted = false", Long.class)
            .setParameter("doctorId", doctorId)
            .getSingleResult();
    }

    @Override
    public long countByPatient(UUID patientId) {
        return em.createQuery("SELECT COUNT(a) FROM AppointmentEntity a WHERE a.patient.id = :patientId AND a.deleted = false", Long.class)
            .setParameter("patientId", patientId)
            .getSingleResult();
    }

    /* -------------------------
     * Delete
     * ------------------------- */

    @Override
    public void delete(AppointmentEntity appointment) {
        appointment.setDeleted(true);
        em.merge(appointment);
    }

    // Implement restore(UUID id) to set deleted = false
    @Override
    public void restore(UUID id) {
        AppointmentEntity entity = em.find(AppointmentEntity.class, id);
        if (entity != null && entity.isDeleted()) {
            entity.setDeleted(false);
            em.merge(entity);
        }
    }

    @Override
    public List<AppointmentEntity> searchByDateRange(String from, String to) {
        return em.createQuery("SELECT a FROM AppointmentEntity a WHERE a.appointmentDate >= :from AND a.appointmentDate <= :to AND a.deleted = false ORDER BY a.appointmentDate DESC", AppointmentEntity.class)
            .setParameter("from", java.time.LocalDate.parse(from))
            .setParameter("to", java.time.LocalDate.parse(to))
            .getResultList();
    }
}
