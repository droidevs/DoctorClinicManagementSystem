/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositories.impl;


import Entities.SpecialisationEntity;
import Repositories.SpecialisationRepository;
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
        String jpql = "SELECT s FROM SpecialisationEntity s WHERE s.name = :name";
        TypedQuery<SpecialisationEntity> query = entityManager.createQuery(jpql, SpecialisationEntity.class);
        query.setParameter("name", name);
        return query.getResultStream().findFirst();
    }

    @Override
    public List<SpecialisationEntity> findAll() {
        String jpql = "SELECT s FROM SpecialisationEntity s";
        TypedQuery<SpecialisationEntity> query = entityManager.createQuery(jpql, SpecialisationEntity.class);
        return query.getResultList();
    }

    @Override
    public boolean existsByName(String name) {
        String jpql = "SELECT COUNT(s) FROM SpecialisationEntity s WHERE s.name = :name";
        Long count = entityManager.createQuery(jpql, Long.class)
                                  .setParameter("name", name)
                                  .getSingleResult();
        return count > 0;
    }

    @Override
    public void delete(SpecialisationEntity specialisation) {
        SpecialisationEntity managedEntity = entityManager.contains(specialisation) 
                ? specialisation 
                : entityManager.merge(specialisation);
        entityManager.remove(managedEntity);
    }
}

