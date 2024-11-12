package com.ali.hunter.web.vm.mapper;

import com.ali.hunter.domain.entity.Participation;

import com.ali.hunter.web.vm.request.ParticipationRequest;
import com.ali.hunter.web.vm.response.CompetitionResultsResponse;
import com.ali.hunter.web.vm.response.ParticipationResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ParticipationVmMapper {

    ParticipationVmMapper INSTANCE = Mappers.getMapper(ParticipationVmMapper.class);

    @Mapping(target = "user.id", source = "userId")
    @Mapping(target = "competition.id", source = "competitionId")
    Participation toParticipation(ParticipationRequest participationRequest);


    @Mapping(target = "username", source = "user.username")
    @Mapping(target = "code", source = "competition.code")
    ParticipationResponse toParticipationResponse(Participation participation);



    @Mapping(target = "location", source = "competition.location")
    @Mapping(target = "date", source = "competition.date")
    @Mapping(target = "score", source = "score")
    List<CompetitionResultsResponse> toParticipationResultResponse(List<Participation> participations);
}

