package com.ali.hunter.service;


import com.ali.hunter.domain.entity.Hunt;
import com.ali.hunter.domain.entity.Participation;
import com.ali.hunter.domain.entity.Species;
import com.ali.hunter.repository.HuntRepository;
import com.ali.hunter.web.vm.request.HuntRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class HuntService {

    @Autowired
    private HuntRepository huntRepository;

    @Autowired
    private ParticipationService participationService;

    @Autowired
    @Lazy
    private SpeciesService speciesService;

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

    public double registerHunt(HuntRequest huntRequest) {

       Participation participation = participationService.findById(huntRequest.getParticipationId());
       Species species = speciesService.findById(huntRequest.getSpeciesId());

        Hunt hunt = Hunt.builder()
                .species(species)
                .participation(participation)
                .weight(huntRequest.getWeight())
                .build();

        huntRepository.save(hunt);

        if (species.getMinimumWeight() <= hunt.getWeight()) {
            return participationService.updateScore(participation);
        }
        return participation.getScore();


    }

    public List<Hunt> findByParticipation(Participation participation) {
        return huntRepository.findByParticipation(participation);
    }
}
