package com.ali.hunter.repository.dto.mapper;

import com.ali.hunter.domain.entity.Competition;
import com.ali.hunter.service.dto.CompetitionDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CompetitionRepoDTOMapper {

    CompetitionDTO toCompetitionDTO(Competition competition);

    List<CompetitionDTO> toCompetitionDTO(List<Competition> competitions);


}
