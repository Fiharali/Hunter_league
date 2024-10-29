package com.ali.hunter.web.vm.mapper;

import com.ali.hunter.domain.entity.Species;
import com.ali.hunter.web.vm.SerchByCategorySpeciesVM;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SpeciesVmMapper {

    SpeciesVmMapper INSTANCE = Mappers.getMapper(SpeciesVmMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "category", source = "category")
    Species toSpecies(SerchByCategorySpeciesVM serchByCategorySpeciesVM);
}
