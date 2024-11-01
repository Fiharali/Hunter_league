package com.ali.hunter.web.vm.response;

import com.ali.hunter.domain.enums.SpeciesType;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class SpeciesResponse {

    private UUID id;

    private String name;

    private SpeciesType category;
}
