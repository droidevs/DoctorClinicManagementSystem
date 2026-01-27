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
        return em.createNamedQuery("Doctor.findByUserId", DoctorEntity.class)
                 .setParameter("userId", userId)
                 .getResultStream()
                 .findFirst();
    }

    @Override
    public List<DoctorEntity> findAll() {
        return em.createNamedQuery("Doctor.findAll", DoctorEntity.class)
                 .getResultList();
    }

    @Override
    public List<DoctorEntity> findAll(int page, int size) {
        return em.createNamedQuery("Doctor.findAll", DoctorEntity.class)
                 .setFirstResult(page * size)
                 .setMaxResults(size)
                 .getResultList();
    }

    @Override
    public List<DoctorEntity> findBySpecialisationId(UUID specialisationId) {
        return em.createNamedQuery("Doctor.findBySpecialisationId", DoctorEntity.class)
                 .setParameter("specialisationId", specialisationId)
                 .getResultList();
    }

    @Override
    public List<DoctorEntity> findByName(String name) {
        return em.createNamedQuery("Doctor.findByName", DoctorEntity.class)
                 .setParameter("name", "%" + name + "%")
                 .getResultList();
    }

    @Override
    public List<DoctorEntity> findBySpecialisation(String specialisationName) {
        return em.createNamedQuery("Doctor.findBySpecialisation", DoctorEntity.class)
                 .setParameter("specialisation", specialisationName)
                 .getResultList();
    }

    @Override
    public List<DoctorEntity> filter(DoctorQuery query) {
        TypedQuery<DoctorEntity> q = em.createNamedQuery("Doctor.filter", DoctorEntity.class)
                .setParameter("name", query.getName() != null ? "%" + query.getName() + "%" : "%")
                .setParameter("email", query.getEmail() != null ? query.getEmail() : "")
                .setParameter("specialisation", query.getSpecialisation() != null ? query.getSpecialisation() : "");
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
