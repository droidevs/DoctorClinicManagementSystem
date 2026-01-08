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
        String jpql = "SELECT p FROM PrescriptionEntity p WHERE p.appointment.id = :appointmentId";
        TypedQuery<PrescriptionEntity> query = entityManager.createQuery(jpql, PrescriptionEntity.class);
        query.setParameter("appointmentId", appointmentId);
        return query.getResultList();
    }

    @Override
    public List<PrescriptionEntity> findAll() {
        String jpql = "SELECT p FROM PrescriptionEntity p";
        TypedQuery<PrescriptionEntity> query = entityManager.createQuery(jpql, PrescriptionEntity.class);
        return query.getResultList();
    }

    @Override
    public void delete(PrescriptionEntity prescription) {
        PrescriptionEntity managedPrescription = entityManager.contains(prescription) 
                ? prescription 
                : entityManager.merge(prescription);
        entityManager.remove(managedPrescription);
    }
}

