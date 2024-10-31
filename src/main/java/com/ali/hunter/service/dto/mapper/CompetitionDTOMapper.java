package com.ali.hunter.service.dto.mapper;

import com.ali.hunter.domain.entity.Competition;
import com.ali.hunter.service.dto.CompetitionDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CompetitionDTOMapper {

    CompetitionDTO toCompetitionDTO(Competition competition);

    List<CompetitionDTO> toCompetitionDTO(List<Competition> competitions);


}
