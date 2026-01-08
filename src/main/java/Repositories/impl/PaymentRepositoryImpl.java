/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositories.impl;


import Entities.PaymentEntity;
import Repositories.PaymentRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
@Transactional
public class PaymentRepositoryImpl
        implements PaymentRepository {

    @PersistenceContext
    EntityManager em;

    /* -------------------------
     * Save
     * ------------------------- */

    @Override
    public PaymentEntity save(PaymentEntity payment) {
        em.persist(payment);
        return payment;
    }

    /* -------------------------
     * Finders
     * ------------------------- */

    @Override
    public Optional<PaymentEntity> findById(UUID id) {
        return Optional.ofNullable(
                em.find(PaymentEntity.class, id)
        );
    }

    @Override
    public Optional<PaymentEntity> findByBillId(UUID billId) {
        return em.createQuery("""
                SELECT p
                FROM PaymentEntity p
                WHERE p.bill.id = :billId
                """, PaymentEntity.class)
                .setParameter("billId", billId)
                .getResultStream()
                .findFirst();
    }

    @Override
    public List<PaymentEntity> findByReceivedBy(UUID secretaryId) {
        return em.createQuery("""
                SELECT p
                FROM PaymentEntity p
                WHERE p.receivedBy.id = :secretaryId
                ORDER BY p.receivedAt DESC
                """, PaymentEntity.class)
                .setParameter("secretaryId", secretaryId)
                .getResultList();
    }

    @Override
    public boolean existsByBillId(UUID billId) {
        Long count = em.createQuery("""
                SELECT COUNT(p)
                FROM PaymentEntity p
                WHERE p.bill.id = :billId
                """, Long.class)
                .setParameter("billId", billId)
                .getSingleResult();

        return count > 0;
    }
}

