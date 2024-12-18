package com.ali.hunter.service;

import com.ali.hunter.config.JwtService;
import com.ali.hunter.domain.entity.User;
import com.ali.hunter.exception.exps.EmailAlreadyExisteException;
import com.ali.hunter.repository.UserRepository;
import com.ali.hunter.web.vm.mapper.UserVmMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private ParticipationService participationService;
    @Mock
    private UserVmMapper userVmMapper;

    @Mock
    private JwtService jwtService;

    @InjectMocks
    private UserService userService;

    @Test
    void addSUser_shouldAddNewUser_whenEmailDoesNotExist() {
        // Arrange
        User user = new User();
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("john.doe@example.com");
        user.setPassword("password123");

        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.empty());
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        User savedUser = userService.addSUser(user);

        // Assert
        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getUsername()).isEqualTo("JohnDoe");
    }

    @Test
    void addSUser_shouldThrowException_whenEmailAlreadyExists() {
        // Arrange
        User user = new User();
        user.setEmail("john.doe@example.com");

        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));

        // Act & Assert
        assertThatThrownBy(() -> userService.addSUser(user))
                .isInstanceOf(EmailAlreadyExisteException.class)
                .hasMessage("Email already exists");
    }
}

