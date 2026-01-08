/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositories.impl;


import Entities.PatientEntity;
import Repositories.PatientRepository;
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
public class PatientRepositoryImpl
        implements PatientRepository {

    @PersistenceContext
    EntityManager em;

    /* -------------------------
     * Save / Update
     * ------------------------- */

    @Override
    public PatientEntity save(PatientEntity patient) {
        em.persist(patient);
        return patient;
    }

    @Override
    public PatientEntity update(PatientEntity patient) {
        return em.merge(patient);
    }

    /* -------------------------
     * Finders
     * ------------------------- */

    @Override
    public Optional<PatientEntity> findById(UUID id) {
        return Optional.ofNullable(
                em.find(PatientEntity.class, id)
        );
    }

    @Override
    public Optional<PatientEntity> findByUserId(UUID userId) {
        TypedQuery<PatientEntity> query =
                em.createQuery("""
                    SELECT p
                    FROM PatientEntity p
                    WHERE p.user.id = :userId
                """, PatientEntity.class);

        return query
                .setParameter("userId", userId)
                .getResultStream()
                .findFirst();
    }

    @Override
    public List<PatientEntity> findAll() {
        return em.createQuery("""
                SELECT p
                FROM PatientEntity p
                ORDER BY p.createdAt DESC
                """, PatientEntity.class)
                .getResultList();
    }

    /* -------------------------
     * Delete
     * ------------------------- */

    @Override
    public void delete(PatientEntity patient) {
        PatientEntity managed =
                em.contains(patient)
                        ? patient
                        : em.merge(patient);

        em.remove(managed);
    }
}
