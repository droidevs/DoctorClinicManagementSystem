/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositories.impl;


import Entities.PrescriptionEntity;
import Repositories.PrescriptionRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Transactional
public class PrescriptionRepositoryImpl implements PrescriptionRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public PrescriptionEntity save(PrescriptionEntity prescription) {
        entityManager.persist(prescription);
        return prescription;
    }

    @Override
    public PrescriptionEntity update(PrescriptionEntity prescription) {
        return entityManager.merge(prescription);
    }

    @Override
    public Optional<PrescriptionEntity> findById(UUID id) {
        PrescriptionEntity prescription = entityManager.find(PrescriptionEntity.class, id);
        return Optional.ofNullable(prescription);
    }

    @Override
    public List<PrescriptionEntity> findByAppointmentId(UUID appointmentId) {
        String jpql = "SELECT p FROM PrescriptionEntity p WHERE p.appointment.id = :appointmentId AND p.deleted = false";
        TypedQuery<PrescriptionEntity> query = entityManager.createQuery(jpql, PrescriptionEntity.class);
        query.setParameter("appointmentId", appointmentId);
        return query.getResultList();
    }

    @Override
    public List<PrescriptionEntity> findAll() {
        String jpql = "SELECT p FROM PrescriptionEntity p WHERE p.deleted = false";
        TypedQuery<PrescriptionEntity> query = entityManager.createQuery(jpql, PrescriptionEntity.class);
        return query.getResultList();
    }

    @Override
    public List<PrescriptionEntity> findAll(int page, int size) {
        String jpql = "SELECT p FROM PrescriptionEntity p WHERE p.deleted = false ORDER BY p.createdAt DESC";
        return entityManager.createQuery(jpql, PrescriptionEntity.class)
            .setFirstResult(page * size)
            .setMaxResults(size)
            .getResultList();
    }

    @Override
    public void delete(PrescriptionEntity prescription) {
        prescription.setDeleted(true);
        entityManager.merge(prescription);
    }

    @Override
    public void restore(UUID id) {
        PrescriptionEntity entity = entityManager.find(PrescriptionEntity.class, id);
        if (entity != null && entity.isDeleted()) {
            entity.setDeleted(false);
            entityManager.merge(entity);
        }
    }

    @Override
    public List<PrescriptionEntity> filter(Criteria.PrescriptionQuery query) {
        StringBuilder jpql = new StringBuilder("SELECT p FROM PrescriptionEntity p WHERE p.deleted = false");
        if (query.getAppointmentId() != null) {
            jpql.append(" AND p.appointment.id = :appointmentId");
        }
        if (query.getPatientId() != null) {
            jpql.append(" AND p.appointment.patient.id = :patientId");
        }
        if (query.getMedicationId() != null) {
            jpql.append(" AND p.medication.id = :medicationId");
        }
        jpql.append(" ORDER BY p.createdAt DESC");
        TypedQuery<PrescriptionEntity> q = entityManager.createQuery(jpql.toString(), PrescriptionEntity.class);
        if (query.getAppointmentId() != null) {
            q.setParameter("appointmentId", query.getAppointmentId());
        }
        if (query.getPatientId() != null) {
            q.setParameter("patientId", query.getPatientId());
        }
        if (query.getMedicationId() != null) {
            q.setParameter("medicationId", query.getMedicationId());
        }
        if (query.getPagination() != null) {
            q.setFirstResult(query.getPagination().page() * query.getPagination().size());
            q.setMaxResults(query.getPagination().size());
        }
        return q.getResultList();
    }
}
