package com.ali.hunter.service;


import com.ali.hunter.domain.entity.Participation;
import com.ali.hunter.domain.entity.Species;
import com.ali.hunter.repository.CompetitionRepository;
import com.ali.hunter.repository.HuntRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class HuntService {

    @Autowired
    private HuntRepository huntRepository;

    @Transactional
    public void deleteBySpecies(UUID id) {
        huntRepository.deleteBySpeciesId(id);
    }

    public void deleteHuntsByParticipation(Participation participation) {
        huntRepository.deleteHuntsByParticipation(participation);
    }

    public void deleteByParticipations(List<Participation> participations) {
        huntRepository.deleteByParticipations(participations);
    }
}
