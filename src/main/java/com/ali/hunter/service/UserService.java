package com.ali.hunter.service;

import com.ali.hunter.domain.entity.User;
import com.ali.hunter.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> searchUsers(String name, String email) {
        return userRepository.findByFirstNameContainingIgnoreCaseOrEmailContainingIgnoreCase(name, email);
    }
}
