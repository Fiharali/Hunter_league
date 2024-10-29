package com.ali.hunter.dto;

import com.ali.hunter.domain.enums.SpeciesType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SpeciesDTO {


    private String name;

    private SpeciesType category;
}
