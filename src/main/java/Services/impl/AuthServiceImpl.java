package Services.impl;

import Dtos.UserDto;
import Entities.UserEntity;
import Repositories.UserRepository;
import Requests.LoginRequest;
import Requests.RegisterUserRequest;
import Services.AuthService;
import Services.JwtService;
import Services.UserService;
import Utils.PasswordHasher;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.UUID;
import Mappers.UserMapper;
import java.util.Optional;

@ApplicationScoped
public class AuthServiceImpl implements AuthService {

    @Inject
    private UserRepository userRepository;

    @Inject
    private UserService userService;

    @Inject
    private JwtService jwtService;

    @Inject
    private UserMapper userMapper;

    @Override
    public String register(RegisterUserRequest request) {
        // Check if email already exists
        if (userRepository.existsByEmail(request.email())) {
            throw new IllegalArgumentException("Email already exists");
        }

        // Create UserEntity
        UserEntity user = new UserEntity();
        user.setUsername(request.email()); // Assuming username is email
        user.setEmail(request.email());
        user.setPasswordHash(PasswordHasher.hash(request.password()));
        // Hardcode role to "patient"
        user.setRole(userService.findRoleByName("patient"));

        // Save user
        user = userRepository.save(user);

        // Create UserDto using mapper
        UserDto userDto = userMapper.toDto(user);

        // Generate JWT token
        String token = jwtService.generateUserToken(userDto);

        return token;
    }

    @Override
    public String login(LoginRequest request) {
        // Find user by email
        UserEntity user = userRepository.findByEmail(request.email())
            .orElseThrow(() -> new IllegalArgumentException("Invalid credentials"));

        if (!user.isEnabled()) {
            throw new IllegalArgumentException("Invalid credentials");
        }

        // Verify password
        if (!PasswordHasher.verify(request.password(), user.getPasswordHash())) {
            throw new IllegalArgumentException("Invalid credentials");
        }

        // Create UserDto using mapper
        UserDto userDto = userMapper.toDto(user);

        // Generate JWT token
        String token = jwtService.generateUserToken(userDto);

        return token;
    }

    @Override
    public void logout() {
        // For JSF, invalidate session
        // For REST, perhaps blacklist token
    }
}
