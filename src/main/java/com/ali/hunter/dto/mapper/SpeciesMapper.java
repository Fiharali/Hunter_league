package com.ali.hunter.dto.mapper;

import com.ali.hunter.domain.entity.Species;
import com.ali.hunter.dto.SpeciesDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SpeciesMapper {

    SpeciesMapper INSTANCE = Mappers.getMapper(SpeciesMapper.class);

    @Mapping(target = "name", source = "name")
    @Mapping(target = "category", source = "category")

    SpeciesDTO toSpeciesDTO(Species species);

    List<SpeciesDTO> toSpeciesDTOList(List<Species> speciesList);
}
