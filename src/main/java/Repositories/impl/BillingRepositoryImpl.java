/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositories.impl;


import Criteria.BillQuery;
import Entities.BillEntity;
import Enums.BillStatus;
import Repositories.BillingRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
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
        return em.createNamedQuery("Bill.findByAppointmentId", BillEntity.class)
                 .setParameter("appointmentId", appointmentId)
                 .getResultStream()
                 .findFirst();
    }

    @Override
    public List<BillEntity> findAll() {
        return em.createNamedQuery("Bill.findAll", BillEntity.class)
                 .getResultList();
    }

    @Override
    public List<BillEntity> findAll(int page, int size) {
        return em.createNamedQuery("Bill.findAll", BillEntity.class)
                 .setFirstResult(page * size)
                 .setMaxResults(size)
                 .getResultList();
    }

    @Override
    public List<BillEntity> findByStatus(BillStatus status) {
        return em.createNamedQuery("Bill.findByStatus", BillEntity.class)
                 .setParameter("status", status)
                 .getResultList();
    }

    @Override
    public List<BillEntity> filter(BillQuery query) {
        TypedQuery<BillEntity> q = em.createNamedQuery("Bill.filter", BillEntity.class)
                .setParameter("status", query.getStatus() != null ? query.getStatus() : "")
                .setParameter("patientId", query.getPatientId())
                .setParameter("doctorId", query.getDoctorId())
                .setParameter("unpaidOnly", query.getUnpaidOnly() != null ? query.getUnpaidOnly() : false)
                .setParameter("fromDate", query.getFromDate())
                .setParameter("toDate", query.getToDate())
                .setParameter("minAmount", query.getMinAmount())
                .setParameter("maxAmount", query.getMaxAmount());
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
    public boolean existsByAppointmentId(UUID appointmentId) {
        Long count = em.createQuery("""
                SELECT COUNT(b)
                FROM BillEntity b
                WHERE b.appointment.id = :appointmentId AND b.deleted = false
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
        bill.setDeleted(true);
        em.merge(bill);
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
    public List<BillEntity> searchByDateRange(String from, String to) {
        String jpql = "SELECT b FROM BillEntity b WHERE b.createdAt >= :from AND b.createdAt <= :to AND b.deleted = false ORDER BY b.createdAt DESC";
        return em.createQuery(jpql, BillEntity.class)
            .setParameter("from", from)
            .setParameter("to", to)
            .getResultList();
    }
}
