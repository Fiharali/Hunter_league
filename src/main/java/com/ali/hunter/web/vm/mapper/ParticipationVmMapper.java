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

    @Mapping(target = "username", source = "user.username")
    ParticipationResponse toParticipationResponse(Participation participation);

    @Mapping(target = "competition.id", source = "competitionId")
    @Mapping(target = "user.email", source = "userEmail")
    Participation toParticipation(ParticipationRequest participationRequest);

    @Mapping(target = "username", source = "user.username")
    List<ParticipationResponse> toParticipationResultResponse(List<Participation> participations);
}