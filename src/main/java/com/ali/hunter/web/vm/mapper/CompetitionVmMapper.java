package com.ali.hunter.web.vm.mapper;

import com.ali.hunter.domain.entity.Competition;
import com.ali.hunter.web.vm.request.CompetitionRequest;
import com.ali.hunter.web.vm.response.CompetitionResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CompetitionVmMapper {

    Competition toCompetition(CompetitionRequest competitionRequest);

    CompetitionResponse toCompetitionResponse(Competition competition);

    List<CompetitionResponse> toCompetitionResponse(List<Competition> competitions);



}
