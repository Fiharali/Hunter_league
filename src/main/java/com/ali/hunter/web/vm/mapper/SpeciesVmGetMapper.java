package com.ali.hunter.web.vm.mapper;

import com.ali.hunter.domain.entity.Species;
import com.ali.hunter.web.vm.SpeciesVM;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SpeciesVmGetMapper {

    SpeciesVmGetMapper INSTANCE = Mappers.getMapper(SpeciesVmGetMapper.class);

    @Mapping(target = "id", ignore = true)
    Species toSpecies(SpeciesVM speciesVM);

}
