package Services.impl;

import Dtos.RoleDto;
import Dtos.UserDto;
import Entities.RoleEntity;
import Entities.UserEntity;
import Mappers.RoleMapper;
import Mappers.UserMapper;
import Repositories.RoleRepository;
import Repositories.UserRepository;
import Services.UserService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@ApplicationScoped
public class UserServiceImpl implements UserService {

    @Inject
    private UserRepository userRepository;

    @Inject
    private RoleRepository roleRepository;

    @Inject
    private UserMapper userMapper;

    @Inject
    private RoleMapper roleMapper;

    @Override
    public UserDto findById(UUID id) {
        UserEntity user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found"));
        return userMapper.toDto(user);
    }

    @Override
    public UserDto findByEmail(String email) {
        UserEntity user = userRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("User not found"));
        return userMapper.toDto(user);
    }

    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll().stream().map(userMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public void disable(UUID id) {
        UserEntity user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found"));
        user.setEnabled(false);
        userRepository.update(user);
    }

    @Override
    public void enable(UUID id) {
        UserEntity user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found"));
        user.setEnabled(true);
        userRepository.update(user);
    }

    @Override
    public UUID resolveUserId(String principalName) {
        return UUID.fromString(principalName);
    }

    @Override
    public Set<RoleDto> getUserRoles(UUID userId) {
        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        RoleDto roleDto = roleMapper.toDto(user.getRole());
        return Set.of(roleDto);
    }

    @Override
    public void assignRole(UUID userId, RoleDto role) {
        // Implement if needed
    }

    @Override
    public void removeRole(UUID userId, RoleDto role) {
        // Implement if needed
    }

    @Override
    public RoleEntity findRoleByName(String roleName) {
        return roleRepository.findByName(roleName).orElseThrow(() -> new IllegalArgumentException("Role not found"));
    }

}
