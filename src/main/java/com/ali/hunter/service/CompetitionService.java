package com.ali.hunter.service;


import com.ali.hunter.domain.entity.Competition;
import com.ali.hunter.repository.CompetitionRepository;
import com.ali.hunter.repository.dto.CompetitionRepoDTO;
import com.ali.hunter.repository.dto.mapper.CompetitionDTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CompetitionService {

    @Autowired
    private CompetitionRepository competitionRepository;


    @Autowired
    private CompetitionDTOMapper competitionDTOMapper;


    public Competition addCompetition(Competition competition) {
        return competitionRepository.save(competition);
    }

    public  Page<CompetitionRepoDTO> getAllCompetition(Pageable pageable) {
        Page<CompetitionRepoDTO> competitionPage = competitionRepository.findAllRepoDTO(pageable);
        List<CompetitionRepoDTO> competitionDTOS = competitionDTOMapper.toCompetitionDTO(competitionPage.getContent());
        return new PageImpl<>(competitionDTOS, pageable, competitionPage.getTotalElements());
    }
}
