package com.ali.hunter.dto.mapper;

import com.ali.hunter.domain.entity.Participation;
import com.ali.hunter.dto.ParticipationDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ParticipationMapper {

    ParticipationMapper INSTANCE = Mappers.getMapper(ParticipationMapper.class);

    @Mapping(target = "user.username", source = "user.username")
    @Mapping(target = "competition.code", source = "competition.code")
    ParticipationDTO toParticipationDTO(Participation participation);
}
