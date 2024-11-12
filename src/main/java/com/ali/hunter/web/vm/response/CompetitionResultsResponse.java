    package com.ali.hunter.web.vm.response;



    import lombok.Getter;
    import lombok.Setter;

    import java.time.LocalDateTime;
    import java.util.UUID;


    @Getter
    @Setter
    public class CompetitionResultsResponse {

        private UUID id;

        private String location;

        private LocalDateTime date;

        private double score;



    }
