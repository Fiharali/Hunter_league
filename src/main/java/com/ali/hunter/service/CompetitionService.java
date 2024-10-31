package com.ali.hunter.service;


import com.ali.hunter.domain.entity.Competition;
import com.ali.hunter.repository.CompetitionRepository;
import com.ali.hunter.service.dto.CompetitionDTO;
import com.ali.hunter.service.dto.mapper.CompetitionDTOMapper;
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
    private ParticipationService participationService;

    @Autowired
    private CompetitionDTOMapper competitionDTOMapper;


    public Competition addCompetition(Competition competition) {
        return competitionRepository.save(competition);
    }

    public  Page<CompetitionDTO> getAllCompetition(Pageable pageable) {
        Page<Competition> competitionPage = competitionRepository.findAll(pageable);
        List<CompetitionDTO> competitionDTOS = competitionDTOMapper.toCompetitionDTO(competitionPage.getContent());
        competitionDTOS.forEach(competitionDTO -> {
            int participationCount = participationService.countByCompetitionId(competitionDTO.getId());
            competitionDTO.setParticipationCount(participationCount);
        });
        return new PageImpl<>(competitionDTOS, pageable, competitionPage.getTotalElements());

    }
}
