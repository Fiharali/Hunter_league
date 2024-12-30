package com.ali.hunter.web.vm.response;



import com.ali.hunter.domain.entity.Participation;
import com.ali.hunter.domain.enums.SpeciesType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@Getter
@Setter
public class CompetitionResponse {


    private UUID id;

    private String location;

    private LocalDateTime date;

    private Integer participationCount = 0;

    private Integer minParticipants ;

    private Integer maxParticipants ;

    private SpeciesType speciesType;








}
