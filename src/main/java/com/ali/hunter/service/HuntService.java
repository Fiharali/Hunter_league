package com.ali.hunter.service;


import com.ali.hunter.domain.entity.Hunt;
import com.ali.hunter.domain.entity.Participation;
import com.ali.hunter.domain.entity.Species;
import com.ali.hunter.exception.exps.ResourceNotFoundException;
import com.ali.hunter.repository.CompetitionRepository;
import com.ali.hunter.repository.HuntRepository;
import com.ali.hunter.repository.ParticipationRepository;
import com.ali.hunter.repository.SpeciesRepository;
import com.ali.hunter.web.vm.request.HuntRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class HuntService {

    @Autowired
    private HuntRepository huntRepository;

    @Autowired
    private ParticipationRepository participationRepository;

    @Autowired
    private SpeciesRepository speciesRepository;

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

    public void registerHunt(HuntRequest huntRequest) {

       Optional<Participation> participation = Optional.ofNullable(participationRepository.findById(huntRequest.getParticipationId()).orElseThrow(() -> new ResourceNotFoundException("Participation not found")));
       Optional<Species> species = Optional.ofNullable(speciesRepository.findById(huntRequest.getSpeciesId()).orElseThrow(() -> new ResourceNotFoundException("Species not found")));

        Hunt hunt = Hunt.builder()
                .species(species.get())
                 .participation(participation.get())
                .weight(huntRequest.getWeight())
                .build();

        huntRepository.save(hunt);

        participation.get().setScore(huntRequest.getWeight() + (huntRequest.getWeight() * species.get().getDifficulty().getValue()) + species.get().getPoints());
        participationRepository.save(participation.get());

       // participationService.updateScore(participation, species,hunt.getWeight());


    }
}
