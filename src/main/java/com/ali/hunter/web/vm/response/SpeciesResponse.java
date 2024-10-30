package com.ali.hunter.web.vm.response;

import com.ali.hunter.domain.enums.SpeciesType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SpeciesResponse {


    private String name;

    private SpeciesType category;
}
