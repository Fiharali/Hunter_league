package com.ali.hunter.web.vm.response;

import com.ali.hunter.domain.enums.Difficulty;
import com.ali.hunter.domain.enums.SpeciesType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class SpeciesResponse {

    private UUID id;

    private String name;

    private SpeciesType category;

    private Double minimumWeight;

    @Enumerated(EnumType.STRING)
    private Difficulty difficulty;
}
