package com.ali.hunter.repository.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class CompetitionRepoDTO {

    private UUID id;

    private String location;

    private LocalDateTime date;

    private Integer participationCount;
}