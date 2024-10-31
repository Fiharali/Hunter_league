package com.ali.hunter.service;

import com.ali.hunter.domain.entity.Competition;
import com.ali.hunter.domain.entity.Participation;
import com.ali.hunter.domain.entity.User;

import com.ali.hunter.exception.ResourceNotFoundException;
import com.ali.hunter.repository.CompetitionRepository;
import com.ali.hunter.repository.ParticipationRepository;
import com.ali.hunter.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ParticipationService {

    @Autowired
    private CompetitionRepository competitionRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ParticipationRepository participationRepository;


    public Participation registerParticipant(@Valid Participation participation) {

        Competition competition = competitionRepository.findById(participation.getCompetition().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Competition not found"));

        User user = userRepository.findById(participation.getUser().getId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Participation participation1 = Participation.builder()
                .user(user)
                .competition(competition)
                .score(0.0)
                .build();

        return participationRepository.save(participation1);
    }

    public Participation getParticipationByCompetitionId(UUID id) {
        return participationRepository.findByCompetitionId(id);
    }

    public Integer countByCompetitionId(UUID id) {
        return participationRepository.countByCompetitionId(id);
    }
}
