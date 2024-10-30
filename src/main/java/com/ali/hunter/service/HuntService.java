package com.ali.hunter.service;


import com.ali.hunter.domain.entity.Species;
import com.ali.hunter.repository.CompetitionRepository;
import com.ali.hunter.repository.HuntRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class HuntService {

    @Autowired
    private HuntRepository huntRepository;


    public void deleteBySpecies(UUID id) {
        huntRepository.deleteBySpeciesId(id);
    }
}
