package com.ali.hunter.web.vm.response;



import com.ali.hunter.domain.entity.Participation;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
public class CompetitionResponse {


    private String location;

    private LocalDateTime date;

    private List<Participation> participations;








}
