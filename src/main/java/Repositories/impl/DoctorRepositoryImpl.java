/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositories.impl;


import Criteria.DoctorQuery;
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
                    WHERE d.user.id = :userId AND d.deleted = false
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
                WHERE d.deleted = false
                ORDER BY d.createdAt DESC
                """, DoctorEntity.class)
                .getResultList();
    }

    @Override
    public List<DoctorEntity> findAll(int page, int size) {
        return em.createQuery("SELECT d FROM DoctorEntity d WHERE d.deleted = false ORDER BY d.createdAt DESC", DoctorEntity.class)
            .setFirstResult(page * size)
            .setMaxResults(size)
            .getResultList();
    }

    @Override
    public List<DoctorEntity> findBySpecialisationId(UUID specialisationId) {
        return em.createQuery("""
                SELECT DISTINCT d
                FROM DoctorEntity d
                JOIN d.specialisations s
                WHERE s.id = :specialisationId AND d.deleted = false
                ORDER BY d.createdAt DESC
                """, DoctorEntity.class)
                .setParameter("specialisationId", specialisationId)
                .getResultList();
    }

    @Override
    public List<DoctorEntity> filter(DoctorQuery query) {
        StringBuilder jpql = new StringBuilder("SELECT d FROM DoctorEntity d WHERE d.deleted = false");
        if (query.getName() != null && !query.getName().isEmpty()) {
            jpql.append(" AND (LOWER(d.firstName) LIKE LOWER(:name) OR LOWER(d.lastName) LIKE LOWER(:name))");
        }
        if (query.getEmail() != null && !query.getEmail().isEmpty()) {
            jpql.append(" AND LOWER(d.email) = LOWER(:email)");
        }
        if (query.getSpecialisation() != null && !query.getSpecialisation().isEmpty()) {
            jpql.append(" AND EXISTS (SELECT 1 FROM d.specialisations s WHERE LOWER(s.name) = LOWER(:specialisation))");
        }
        jpql.append(" ORDER BY d.createdAt DESC");
        TypedQuery<DoctorEntity> q = em.createQuery(jpql.toString(), DoctorEntity.class);
        if (query.getName() != null && !query.getName().isEmpty()) {
            q.setParameter("name", "%" + query.getName() + "%");
        }
        if (query.getEmail() != null && !query.getEmail().isEmpty()) {
            q.setParameter("email", query.getEmail());
        }
        if (query.getSpecialisation() != null && !query.getSpecialisation().isEmpty()) {
            q.setParameter("specialisation", query.getSpecialisation());
        }
        if (query.getPagination() != null) {
            q.setFirstResult(query.getPagination().page() * query.getPagination().size());
            q.setMaxResults(query.getPagination().size());
        }
        return q.getResultList();
    }

    /* -------------------------
     * Delete
     * ------------------------- */

    @Override
    public void delete(DoctorEntity doctor) {
        doctor.setDeleted(true);
        em.merge(doctor);
    }

    /* -------------------------
     * Delete by ID (added)
     * ------------------------- */

    @Override
    public void deleteById(UUID doctorId) {
        em.createQuery("""
                UPDATE DoctorEntity d
                SET d.deleted = true
                WHERE d.id = :doctorId
                """)
          .setParameter("doctorId", doctorId)
          .executeUpdate();
    }

    @Override
    public void restore(UUID id) {
        DoctorEntity entity = em.find(DoctorEntity.class, id);
        if (entity != null && entity.isDeleted()) {
            entity.setDeleted(false);
            em.merge(entity);
        }
    }
}
