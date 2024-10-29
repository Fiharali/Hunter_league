package com.ali.hunter.dto;

import com.ali.hunter.domain.entity.Competition;
import com.ali.hunter.domain.entity.User;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ParticipationDTO {

    private User user;
    private Competition competition;


}
