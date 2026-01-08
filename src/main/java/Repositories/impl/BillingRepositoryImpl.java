/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositories.impl;


import Entities.BillEntity;
import Enums.BillStatus;
import Repositories.BillingRepository;
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
public class BillingRepositoryImpl
        implements BillingRepository {

    @PersistenceContext
    EntityManager em;

    /* -------------------------
     * Save / Update
     * ------------------------- */

    @Override
    public BillEntity save(BillEntity bill) {
        em.persist(bill);
        return bill;
    }

    @Override
    public BillEntity update(BillEntity bill) {
        return em.merge(bill);
    }

    /* -------------------------
     * Finders
     * ------------------------- */

    @Override
    public Optional<BillEntity> findById(UUID id) {
        return Optional.ofNullable(
                em.find(BillEntity.class, id)
        );
    }

    @Override
    public Optional<BillEntity> findByAppointmentId(UUID appointmentId) {
        TypedQuery<BillEntity> query =
                em.createQuery("""
                    SELECT b
                    FROM BillEntity b
                    WHERE b.appointment.id = :appointmentId
                """, BillEntity.class);

        return query
                .setParameter("appointmentId", appointmentId)
                .getResultStream()
                .findFirst();
    }

    @Override
    public List<BillEntity> findAll() {
        return em.createQuery("""
                SELECT b
                FROM BillEntity b
                ORDER BY b.generatedAt DESC
                """, BillEntity.class)
                .getResultList();
    }

    @Override
    public List<BillEntity> findByStatus(BillStatus status) {
        return em.createQuery("""
                SELECT b
                FROM BillEntity b
                WHERE b.status = :status
                ORDER BY b.generatedAt DESC
                """, BillEntity.class)
                .setParameter("status", status)
                .getResultList();
    }

    /* -------------------------
     * Existence checks
     * ------------------------- */

    @Override
    public boolean existsByAppointmentId(UUID appointmentId) {
        Long count = em.createQuery("""
                SELECT COUNT(b)
                FROM BillEntity b
                WHERE b.appointment.id = :appointmentId
                """, Long.class)
                .setParameter("appointmentId", appointmentId)
                .getSingleResult();

        return count > 0;
    }

    /* -------------------------
     * Delete
     * ------------------------- */

    @Override
    public void delete(BillEntity bill) {
        BillEntity managed =
                em.contains(bill)
                        ? bill
                        : em.merge(bill);

        em.remove(managed);
    }
}
