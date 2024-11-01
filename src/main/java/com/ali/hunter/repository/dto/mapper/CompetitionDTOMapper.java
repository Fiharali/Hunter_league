package com.ali.hunter.repository.dto.mapper;

import com.ali.hunter.domain.entity.Competition;
import com.ali.hunter.repository.dto.CompetitionRepoDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CompetitionDTOMapper {

    CompetitionRepoDTO toCompetitionDTO(Competition competition);

    List<CompetitionRepoDTO> toCompetitionDTO(List<CompetitionRepoDTO> content);
}
