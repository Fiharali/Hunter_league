package com.ali.hunter.web.vm.mapper;

import com.ali.hunter.domain.entity.Species;
import com.ali.hunter.web.vm.request.SerchByCategorySpeciesRequest;
import com.ali.hunter.web.vm.request.SpeciesRequest;
import com.ali.hunter.web.vm.response.SpeciesResponse;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface SpeciesMapperVm {

    Species toSpecies(SpeciesRequest speciesRequest);

    Species toSpecies(SerchByCategorySpeciesRequest serchByCategorySpeciesRequest);

    SpeciesResponse toSpeciesResponse(Species species);

    List<SpeciesResponse> toSpeciesResponseList(List<Species> speciesList);



}