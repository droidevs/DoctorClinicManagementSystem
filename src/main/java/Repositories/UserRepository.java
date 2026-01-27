/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Repositories;


import Entities.UserEntity;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository {

    UserEntity save(UserEntity user);

    UserEntity update(UserEntity user);

    Optional<UserEntity> findById(UUID id);

    Optional<UserEntity> findByEmail(String email);

    List<UserEntity> findAll();

    Optional<UserEntity> findByUsername(String username);

    List<UserEntity> findAllActive();

    List<UserEntity> findByRole(String roleName);

    boolean existsByEmail(String email);

    void delete(UserEntity user);
}
