package com.ali.hunter.service;

import com.ali.hunter.config.JwtService;
import com.ali.hunter.domain.entity.Competition;
import com.ali.hunter.domain.entity.Participation;
import com.ali.hunter.domain.entity.User;
import com.ali.hunter.exception.exps.EmailAlreadyExisteException;
import com.ali.hunter.exception.exps.InvalidPasswordException;
import com.ali.hunter.exception.exps.ResourceNotFoundException;
import com.ali.hunter.repository.UserRepository;
import com.ali.hunter.utils.PasswordUtil;

import com.ali.hunter.web.vm.mapper.UserVmMapper;
import com.ali.hunter.web.vm.request.AuthRequest;
import com.ali.hunter.web.vm.response.AuthResponse;
import com.ali.hunter.web.vm.response.UserHistoryResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ParticipationService participationService;
    private final UserVmMapper userVmMapper;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;


    public Page<User> searchUsers(User user , Pageable pageable) {
        if (user.getFirstName() == null  &&
                user.getLastName() == null &&
                user.getCin() == null &&
                user.getEmail() == null ) {
            return userRepository.findAll(pageable);
        }

        ExampleMatcher matcher = ExampleMatcher.matchingAll()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example<User> example = Example.of(user, matcher);

        return userRepository.findAll(example, pageable);


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
        userRepository.deleteUser(userToDelete.getId());
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

    public Page<UserHistoryResponse> getUserCompetitionHistory(UUID userId, Pageable pageable) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Page<Participation> participations = participationService.findByUserOrderByCompetitionDateDesc(user, pageable);

        List<UserHistoryResponse> historyResponses = participations.stream()
                .map(participation -> {
                    int rank = calculateRank(participation.getCompetition(), participation.getScore());
                    return mapToUserHistoryResponse(participation, rank);
                })
                .collect(Collectors.toList());

        return new PageImpl<>(historyResponses, pageable, participations.getTotalElements());
    }

    private int calculateRank(Competition competition, double userScore) {
        List<Participation> participations = participationService.findByCompetitionOrderByScoreDesc(competition);

        for (int i = 0; i < participations.size(); i++) {
            if (participations.get(i).getScore() == userScore) {
                return i + 1;
            }
        }
        return -1;
    }


    private UserHistoryResponse mapToUserHistoryResponse(Participation participation, int rank) {
        UserHistoryResponse response = new UserHistoryResponse();
        response.setId(participation.getId());
        response.setLocation(participation.getCompetition().getLocation());
        response.setDate(participation.getCompetition().getDate());
        response.setScore(participation.getScore());
        response.setRank(rank);
        return response;
    }



    public AuthResponse loginAuth(AuthRequest authRequest) {
        try {
            User user = userRepository.findByEmail(authRequest.getEmail())
                    .orElseThrow(() -> new BadCredentialsException("Email not found. Please check your email address."));

            try {
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                authRequest.getEmail(),
                                authRequest.getPassword()
                        )
                );
            } catch (BadCredentialsException e) {
                throw new BadCredentialsException("Incorrect password. Please try again.");
            }

            var token = jwtService.generateToken(user);

            return AuthResponse.builder()
                    .token(token)
                    .build();

        } catch (BadCredentialsException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Authentication failed", e);
        }
    }


}
