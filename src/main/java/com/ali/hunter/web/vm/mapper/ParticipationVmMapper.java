package com.ali.hunter.web.vm.mapper;

import com.ali.hunter.domain.entity.Participation;

import com.ali.hunter.web.vm.ParticipationVM;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ParticipationVmMapper {

    ParticipationVmMapper INSTANCE = Mappers.getMapper(ParticipationVmMapper.class);
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user.id", source = "userId")
    @Mapping(target = "competition.id", source = "competitionId")
    Participation toParticipation(ParticipationVM participationVM);
}

