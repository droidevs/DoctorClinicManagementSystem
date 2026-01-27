package Repositories;

import Entities.RoleEntity;
import java.util.Optional;

public interface RoleRepository {
    Optional<RoleEntity> findByName(String name);
}
