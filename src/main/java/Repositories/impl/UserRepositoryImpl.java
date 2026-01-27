/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositories.impl;


import Entities.UserEntity;
import Repositories.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Transactional
public class UserRepositoryImpl implements UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public UserEntity save(UserEntity user) {
        entityManager.persist(user);
        return user;
    }

    @Override
    public UserEntity update(UserEntity user) {
        return entityManager.merge(user);
    }

    @Override
    public Optional<UserEntity> findById(UUID id) {
        UserEntity user = entityManager.find(UserEntity.class, id);
        return Optional.ofNullable(user);
    }

    @Override
    public Optional<UserEntity> findByEmail(String email) {
        return entityManager.createNamedQuery("User.findByEmail", UserEntity.class)
                            .setParameter("email", email)
                            .getResultStream()
                            .findFirst();
    }

    @Override
    public List<UserEntity> findAll() {
        return entityManager.createNamedQuery("User.findAll", UserEntity.class)
                            .getResultList();
    }

    @Override
    public Optional<UserEntity> findByUsername(String username) {
        return entityManager.createNamedQuery("User.findByUsername", UserEntity.class)
                            .setParameter("username", username)
                            .getResultStream()
                            .findFirst();
    }

    @Override
    public List<UserEntity> findAllActive() {
        return entityManager.createNamedQuery("User.findAllActive", UserEntity.class)
                            .getResultList();
    }

    @Override
    public List<UserEntity> findByRole(String roleName) {
        return entityManager.createNamedQuery("User.findByRole", UserEntity.class)
                            .setParameter("roleName", roleName)
                            .getResultList();
    }

    @Override
    public boolean existsByEmail(String email) {
        String jpql = "SELECT COUNT(u) FROM UserEntity u WHERE u.email = :email";
        Long count = entityManager.createQuery(jpql, Long.class)
                                  .setParameter("email", email)
                                  .getSingleResult();
        return count > 0;
    }

    @Override
    public void delete(UserEntity user) {
        UserEntity managedUser = entityManager.contains(user) ? user : entityManager.merge(user);
        entityManager.remove(managedUser);
    }
}
