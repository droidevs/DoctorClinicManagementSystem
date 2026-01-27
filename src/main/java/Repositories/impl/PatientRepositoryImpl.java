/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositories.impl;


import Criteria.PatientQuery;
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
                    WHERE p.user.id = :userId AND p.deleted = false
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
                WHERE p.deleted = false
                ORDER BY p.createdAt DESC
                """, PatientEntity.class)
                .getResultList();
    }

    @Override
    public List<PatientEntity> findAll(int page, int size) {
        return em.createQuery("SELECT p FROM PatientEntity p WHERE p.deleted = false ORDER BY p.createdAt DESC", PatientEntity.class)
            .setFirstResult(page * size)
            .setMaxResults(size)
            .getResultList();
    }

    @Override
    public List<PatientEntity> filter(PatientQuery query) {
        StringBuilder jpql = new StringBuilder("SELECT p FROM PatientEntity p WHERE p.deleted = false");
        if (query.getName() != null && !query.getName().isEmpty()) {
            jpql.append(" AND (LOWER(p.firstName) LIKE LOWER(:name) OR LOWER(p.lastName) LIKE LOWER(:name))");
        }
        if (query.getEmail() != null && !query.getEmail().isEmpty()) {
            jpql.append(" AND LOWER(p.email) = LOWER(:email)");
        }
        jpql.append(" ORDER BY p.createdAt DESC");
        TypedQuery<PatientEntity> q = em.createQuery(jpql.toString(), PatientEntity.class);
        if (query.getName() != null && !query.getName().isEmpty()) {
            q.setParameter("name", "%" + query.getName() + "%");
        }
        if (query.getEmail() != null && !query.getEmail().isEmpty()) {
            q.setParameter("email", query.getEmail());
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
    public void delete(PatientEntity patient) {
        patient.setDeleted(true);
        em.merge(patient);
    }

    @Override
    public void softDelete(UUID id) {
        // TODO: Implement soft delete logic if supported
    }

    @Override
    public void restore(UUID id) {
        // TODO: Implement restore logic if supported
    }

    @Override
    public List<PatientEntity> searchByName(String name) {
        return em.createQuery("SELECT p FROM PatientEntity p WHERE (LOWER(p.firstName) LIKE LOWER(:name) OR LOWER(p.lastName) LIKE LOWER(:name)) AND p.deleted = false ORDER BY p.createdAt DESC", PatientEntity.class)
            .setParameter("name", "%" + name + "%")
            .getResultList();
    }
}
