package com.ali.hunter.service;

import com.ali.hunter.domain.entity.Competition;
import com.ali.hunter.domain.entity.Participation;
import com.ali.hunter.domain.entity.User;

import com.ali.hunter.exception.exps.LicenseExpirationDateException;
import com.ali.hunter.exception.exps.MaxParticipantsException;
import com.ali.hunter.exception.exps.RegistrationClosedException;
import com.ali.hunter.exception.exps.ResourceNotFoundException;
import com.ali.hunter.repository.CompetitionRepository;
import com.ali.hunter.repository.ParticipationRepository;
import com.ali.hunter.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class ParticipationService {

    @Autowired
    private CompetitionService competitionService;
    @Autowired
    private UserRepository userService;
    @Autowired
    private ParticipationRepository participationRepository;
    @Autowired
    private HuntService huntService;


    public Participation registerParticipant(@Valid Participation participation) {

        Competition competition = competitionService.findById(participation.getCompetition().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Competition not found"));

        User user = userService.findById(participation.getUser().getId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));


        if (user.getLicenseExpirationDate().isBefore(LocalDateTime.now())){
            throw new LicenseExpirationDateException("User license is expired");
        }

        if (competition.getParticipations().size() >= competition.getMaxParticipants()) {
            throw new MaxParticipantsException("Max participants reached");
        }

        if (!competition.getOpenRegistration()) {
            throw new RegistrationClosedException(" Registration is closed");
        }

        Participation participation1 = Participation.builder()
                .user(user)
                .competition(competition)
                .score(0.0)
                .build();

        return participationRepository.save(participation1);
    }

    @Transactional
    public void deleteParticipationsByUser(User userToDelete) {

        List<Participation> participations = participationRepository.findByUser(userToDelete);

        for (Participation participation : participations) {
            huntService.deleteHuntsByParticipation(participation);
            participationRepository.delete(participation);
        }

    }
}
