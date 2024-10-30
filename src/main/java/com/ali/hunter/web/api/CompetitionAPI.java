package com.ali.hunter.web.api;

import com.ali.hunter.domain.entity.Competition;

import com.ali.hunter.service.CompetitionService;
import com.ali.hunter.web.vm.mapper.CompetitionVmMapper;
import com.ali.hunter.web.vm.request.CompetitionRequest;
import com.ali.hunter.web.vm.response.CompetitionResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/competitions")
@RequiredArgsConstructor
public class CompetitionAPI {

    private final CompetitionVmMapper competitionVmMapper;
    private final CompetitionService competitionService;



    @PostMapping
    public ResponseEntity<CompetitionResponse> addCompetition(
            @Valid @RequestBody CompetitionRequest competitionRequest) {

        Competition competitionEntity = competitionVmMapper.toCompetition(competitionRequest);
        Competition competition = competitionService.addCompetition(competitionEntity);

        return ResponseEntity.ok(competitionVmMapper.toCompetitionResponse(competition));
    }
}
