package com.ali.hunter.service;

import com.ali.hunter.domain.entity.User;
import com.ali.hunter.exception.exps.EmailAlreadyExisteException;
import com.ali.hunter.exception.exps.InvalidPasswordException;
import com.ali.hunter.exception.exps.ResourceNotFoundException;
import com.ali.hunter.repository.UserRepository;
import com.ali.hunter.utils.PasswordUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ParticipationService participationService;


    public Page<User> searchUsers(User user , Pageable pageable) {
        if (user.getFirstName() == null  &&
                user.getLastName() == null &&
                user.getCin() == null &&
                user.getEmail() == null ) {
            return userRepository.findAll(pageable);
        }
        return  userRepository.findByCinContainingIgnoreCaseOrEmailContainingIgnoreCaseOrFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase( user.getCin() ,user.getFirstName(), user.getLastName(), user.getEmail()  , pageable);
    }


    public User addSUser(User user) {

        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new EmailAlreadyExisteException("Email already exists");
        }

        user.setJoinDate(LocalDateTime.now());
        user.setUsername(user.getFirstName()+user.getLastName());
        user.setPassword(PasswordUtil.hashPassword(user.getPassword()));

        return userRepository.save(user);
    }

    public User updateUser(UUID id, User user) {
        User userToUpdate = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if (!userToUpdate.getEmail().equals(user.getEmail())) {
            if (userRepository.findByEmail(user.getEmail()).isPresent()) {
                throw new EmailAlreadyExisteException("Email already exists");
            }

        }

        userToUpdate.setFirstName(user.getFirstName());
        userToUpdate.setLastName(user.getLastName());
        userToUpdate.setUsername(user.getFirstName()+user.getLastName());
        userToUpdate.setCin(user.getCin());
        userToUpdate.setEmail(user.getEmail());
        userToUpdate.setPassword(PasswordUtil.hashPassword(user.getPassword()));
        userToUpdate.setNationality(user.getNationality());
        userToUpdate.setLicenseExpirationDate(user.getLicenseExpirationDate());
        userToUpdate.setRole(user.getRole());

        return userRepository.save(userToUpdate);

    }

    @Transactional
    public User deleteUser(User user) {
        User userToDelete = userRepository.findById(user.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

       participationService.deleteParticipationsByUser(userToDelete);
        userRepository.delete(userToDelete);
        //userRepository.deleteUserWithRelatedData(user.getId());
        return userToDelete;
    }

    public Optional<User> findById(UUID id) {
        return userRepository.findById(id);
    }

    public User login(User user) {
        User userEntity = userRepository.findByEmail(user.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("this email does not exist"));

        if (!PasswordUtil.checkPassword(user.getPassword(), userEntity.getPassword())) {
            throw new InvalidPasswordException("this password does not match ");
        }

        return userEntity;
    }
}
