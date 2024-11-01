package com.ali.hunter.web.vm.request;


import com.ali.hunter.domain.enums.SpeciesType;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CompetitionRequest {


    @NotNull(message = "you must enter the code of the competition")
    private String code;

    @NotNull(message = "you must enter the location of the competition")
    private String location;

    @NotNull(message = "you must enter the date of the competition")
    private LocalDateTime date;

    @NotNull(message = "you must enter the speciesType of the competition")
    private SpeciesType speciesType;

    @NotNull(message = "you must enter the minParticipants of the competition")
    private Integer minParticipants;

    @NotNull(message = "you must enter the maxParticipants of the competition")
    private Integer maxParticipants;

    @NotNull(message = "you must enter the openRegistration of the competition")
    private Boolean openRegistration;


    @AssertTrue(message = "maxParticipants must be greater than minParticipants")
    public boolean isMaxGreaterThanMin() {
        if (minParticipants == null || maxParticipants == null) {
            return true;
        }
        return maxParticipants > minParticipants;
    }

}
