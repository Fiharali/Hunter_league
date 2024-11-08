package com.ali.hunter.service;


import com.ali.hunter.domain.entity.Competition;
import com.ali.hunter.domain.entity.User;
import com.ali.hunter.exception.exps.CompetitionAlreadyExistsException;
import com.ali.hunter.repository.CompetitionRepository;
import com.ali.hunter.repository.dto.CompetitionRepoDTO;
import com.ali.hunter.repository.dto.mapper.CompetitionDTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class CompetitionService {

    @Autowired
    private CompetitionRepository competitionRepository;


    @Autowired
    private CompetitionDTOMapper competitionDTOMapper;


    public Competition addCompetition(Competition competition) {
        LocalDateTime competitionDate = competition.getDate();

        LocalDateTime startOfWeek = competitionDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        LocalDateTime endOfWeek = competitionDate.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));

        Optional<Competition> existingCompetition = competitionRepository
                .findCompetitionByDateRange(startOfWeek, endOfWeek);
        if (existingCompetition.isPresent()) {
            throw new CompetitionAlreadyExistsException("Competition already exists for this week");
        }

        return competitionRepository.save(competition);
    }

    public  Page<CompetitionRepoDTO> getAllCompetition(Pageable pageable) {
        Page<CompetitionRepoDTO> competitionPage = competitionRepository.findAllRepoDTO(pageable);
        List<CompetitionRepoDTO> competitionDTOS = competitionDTOMapper.toCompetitionDTO(competitionPage.getContent());
        return new PageImpl<>(competitionDTOS, pageable, competitionPage.getTotalElements());
    }


    public Optional<Competition> findById(UUID id) {
        return competitionRepository.findById(id);
    }
}
