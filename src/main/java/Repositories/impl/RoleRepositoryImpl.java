package Repositories.impl;

import Entities.RoleEntity;
import Repositories.RoleRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import java.util.Optional;

public class RoleRepositoryImpl implements RoleRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Optional<RoleEntity> findByName(String name) {
        TypedQuery<RoleEntity> query = em.createQuery("SELECT r FROM RoleEntity r WHERE r.name = :name", RoleEntity.class);
        query.setParameter("name", name);
        return query.getResultStream().findFirst();
    }
}
