package com.ali.hunter.service;


import com.ali.hunter.domain.entity.Competition;
import com.ali.hunter.repository.CompetitionRepository;
import com.ali.hunter.repository.HuntRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CompetitionService {

    @Autowired
    private CompetitionRepository competitionRepository;


    public Competition addCompetition(Competition competition) {
        return competitionRepository.save(competition);
    }
}
