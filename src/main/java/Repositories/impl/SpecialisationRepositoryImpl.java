/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositories.impl;


import Entities.SpecialisationEntity;
import Repositories.SpecialisationRepository;
import Criteria.SpecialisationQuery;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Transactional
public class SpecialisationRepositoryImpl implements SpecialisationRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public SpecialisationEntity save(SpecialisationEntity specialisation) {
        entityManager.persist(specialisation);
        return specialisation;
    }

    @Override
    public SpecialisationEntity update(SpecialisationEntity specialisation) {
        return entityManager.merge(specialisation);
    }

    @Override
    public Optional<SpecialisationEntity> findById(UUID id) {
        SpecialisationEntity entity = entityManager.find(SpecialisationEntity.class, id);
        return Optional.ofNullable(entity);
    }

    @Override
    public Optional<SpecialisationEntity> findByName(String name) {
        String jpql = "SELECT s FROM SpecialisationEntity s WHERE s.name = :name AND s.deleted = false";
        TypedQuery<SpecialisationEntity> query = entityManager.createQuery(jpql, SpecialisationEntity.class);
        query.setParameter("name", name);
        return query.getResultStream().findFirst();
    }

    @Override
    public List<SpecialisationEntity> findAll() {
        String jpql = "SELECT s FROM SpecialisationEntity s WHERE s.deleted = false";
        TypedQuery<SpecialisationEntity> query = entityManager.createQuery(jpql, SpecialisationEntity.class);
        return query.getResultList();
    }

    @Override
    public List<SpecialisationEntity> findAll(int page, int size) {
        String jpql = "SELECT s FROM SpecialisationEntity s WHERE s.deleted = false ORDER BY s.name ASC";
        return entityManager.createQuery(jpql, SpecialisationEntity.class)
            .setFirstResult(page * size)
            .setMaxResults(size)
            .getResultList();
    }

    @Override
    public boolean existsByName(String name) {
        String jpql = "SELECT COUNT(s) FROM SpecialisationEntity s WHERE s.name = :name AND s.deleted = false";
        Long count = entityManager.createQuery(jpql, Long.class)
                                  .setParameter("name", name)
                                  .getSingleResult();
        return count > 0;
    }

    @Override
    public void delete(SpecialisationEntity specialisation) {
        specialisation.setDeleted(true);
        entityManager.merge(specialisation);
    }

    @Override
    public List<SpecialisationEntity> filter(SpecialisationQuery query) {
        StringBuilder jpql = new StringBuilder("SELECT s FROM SpecialisationEntity s WHERE s.deleted = false");
        if (query.getSearchQuery() != null && !query.getSearchQuery().isEmpty()) {
            jpql.append(" AND LOWER(s.name) LIKE LOWER(:searchQuery)");
        }
        jpql.append(" ORDER BY s.name ASC");
        TypedQuery<SpecialisationEntity> q = entityManager.createQuery(jpql.toString(), SpecialisationEntity.class);
        if (query.getSearchQuery() != null && !query.getSearchQuery().isEmpty()) {
            q.setParameter("searchQuery", "%" + query.getSearchQuery() + "%");
        }
        if (query.getPagination() != null) {
            q.setFirstResult(query.getPagination().page() * query.getPagination().size());
            q.setMaxResults(query.getPagination().size());
        }
        return q.getResultList();
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
    public List<SpecialisationEntity> searchByName(String name) {
        String jpql = "SELECT s FROM SpecialisationEntity s WHERE LOWER(s.name) LIKE LOWER(:name) AND s.deleted = false ORDER BY s.name ASC";
        return entityManager.createQuery(jpql, SpecialisationEntity.class)
            .setParameter("name", "%" + name + "%")
            .getResultList();
    }
}
