/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositories.impl;


import Entities.DoctorEntity;
import Repositories.DoctorRepository;
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
public class DoctorRepositoryImpl
        implements DoctorRepository {

    @PersistenceContext
    EntityManager em;

    /* -------------------------
     * Save / Update
     * ------------------------- */

    @Override
    public DoctorEntity save(DoctorEntity doctor) {
        em.persist(doctor);
        return doctor;
    }

    @Override
    public DoctorEntity update(DoctorEntity doctor) {
        return em.merge(doctor);
    }

    /* -------------------------
     * Finders
     * ------------------------- */

    @Override
    public Optional<DoctorEntity> findById(UUID id) {
        return Optional.ofNullable(
                em.find(DoctorEntity.class, id)
        );
    }

    @Override
    public Optional<DoctorEntity> findByUserId(UUID userId) {
        TypedQuery<DoctorEntity> query =
                em.createQuery("""
                    SELECT d
                    FROM DoctorEntity d
                    WHERE d.user.id = :userId
                """, DoctorEntity.class);

        return query
                .setParameter("userId", userId)
                .getResultStream()
                .findFirst();
    }

    @Override
    public List<DoctorEntity> findAll() {
        return em.createQuery("""
                SELECT d
                FROM DoctorEntity d
                ORDER BY d.createdAt DESC
                """, DoctorEntity.class)
                .getResultList();
    }

    @Override
    public List<DoctorEntity> findBySpecialisationId(UUID specialisationId) {
        return em.createQuery("""
                SELECT DISTINCT d
                FROM DoctorEntity d
                JOIN d.specialisations s
                WHERE s.id = :specialisationId
                ORDER BY d.createdAt DESC
                """, DoctorEntity.class)
                .setParameter("specialisationId", specialisationId)
                .getResultList();
    }

    /* -------------------------
     * Delete
     * ------------------------- */

    @Override
    public void delete(DoctorEntity doctor) {
        DoctorEntity managed =
                em.contains(doctor)
                        ? doctor
                        : em.merge(doctor);

        em.remove(managed);
    }

    /* -------------------------
     * Delete by ID (added)
     * ------------------------- */

    @Override
    public void deleteById(UUID doctorId) {
        em.createQuery("""
                DELETE
                FROM DoctorEntity d
                WHERE d.id = :doctorId
                """)
          .setParameter("doctorId", doctorId)
          .executeUpdate();
    }
}

