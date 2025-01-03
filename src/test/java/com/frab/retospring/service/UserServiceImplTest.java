package com.frab.retospring.service;

import com.frab.retospring.constants.ErrorConstant;
import com.frab.retospring.dto.UserRequest;
import com.frab.retospring.dto.UserResponse;
import com.frab.retospring.exception.exception.RequestException;
import com.frab.retospring.model.RoleEnum;
import com.frab.retospring.model.User;
import com.frab.retospring.repository.RoleRepository;
import com.frab.retospring.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreate_userAlreadyExists() {
        // Simula un usuario existente en la base de datos
        UserRequest userRequest = new UserRequest("Frank Abad","frank@abad.com", "password123",null);
        when(userRepository.getByEmail(userRequest.getEmail())).thenReturn(new User());

        // Verifica que se lance la excepción esperada
        RequestException exception = assertThrows(RequestException.class,
                () -> userService.create(userRequest));
        assertEquals(ErrorConstant.EMAIL_EXISTS, exception.getMessage());

        // Verifica que no se guarde ningún usuario
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void testCreate_successfulCreation() {
        // Simula un UserRequest válido
        UserRequest userRequest = new UserRequest("Frank Abad","frank@abad.com", "qwerty2024",null);
        when(userRepository.getByEmail(userRequest.getEmail())).thenReturn(null); // No existe
        when(passwordEncoder.encode(userRequest.getPassword())).thenReturn("encodedPassword");
        when(roleRepository.findByRoleEnum(RoleEnum.ADMIN)).thenReturn(Optional.empty());

        UUID uuid = UUID.randomUUID();
        User savedUser = new User();
        savedUser.setUuid(uuid);
        savedUser.setName("Frank Abad");
        savedUser.setEmail("frank@abad.com");
        savedUser.setPassword("$2a$10$Gomyez47bqnLJsMkSgnYTeE7ut0jWu7idvsIn44.o0ade2OZYdUIq");
        when(userRepository.save(any(User.class))).thenReturn(savedUser);

        // Ejecuta el método
        UserResponse response = userService.create(userRequest);

        // Verificaciones sobre el resultado
        assertNotNull(response);
        assertEquals(uuid, response.getUuid());

        // Verifica que se llamó a los métodos esperados
        verify(userRepository, times(1)).getByEmail(userRequest.getEmail());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testCreate_assignsCorrectValues() {
        // Simula un UserRequest válido
        UserRequest userRequest = new UserRequest("Frank Abad","frank@abad.com", "password123",null);
        when(userRepository.getByEmail(userRequest.getEmail())).thenReturn(null); // No existe
        when(passwordEncoder.encode(userRequest.getPassword())).thenReturn("encodedPassword");
        when(roleRepository.findByRoleEnum(RoleEnum.ADMIN)).thenReturn(Optional.empty());
        UUID uuid = UUID.randomUUID();
        User savedUser = new User();
        savedUser.setUuid(uuid);
        savedUser.setEmail("frank@abad.com");
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> {
            User user = invocation.getArgument(0);
            user.setUuid(uuid);
            return user;
        });

        // Ejecuta el método
        UserResponse response = userService.create(userRequest);

        // Verifica que se asignaron los valores correctos al usuario antes de guardarlo
        verify(userRepository).save(argThat(user ->
                user.getEmail().equals("frank@abad.com") &&
                        user.getName().equals("Frank Abad") &&
                        user.isActive() &&
                        user.getCreated() != null &&
                        user.getModified() != null &&
                        user.getLastLogin() != null
        ));
    }

}
