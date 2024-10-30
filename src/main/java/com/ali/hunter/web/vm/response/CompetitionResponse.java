package com.ali.hunter.web.vm.response;



import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
public class CompetitionResponse {

    private String code;

    private String location;

    private LocalDateTime date;



}
