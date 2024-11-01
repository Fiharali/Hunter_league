package com.ali.hunter.web.api;

import com.ali.hunter.domain.entity.Participation;

import com.ali.hunter.service.ParticipationService;
import com.ali.hunter.web.vm.request.ParticipationRequest;
import com.ali.hunter.web.vm.mapper.ParticipationVmMapper;
import com.ali.hunter.web.vm.response.ParticipationResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/participations")
public class ParticipationAPI {

    private final ParticipationService participationService;
    private final ParticipationVmMapper participationVmMapper;


    public ParticipationAPI(ParticipationService participationService, ParticipationVmMapper participationVmMapper) {
        this.participationService = participationService;
        this.participationVmMapper = participationVmMapper;
    }


    @PostMapping("/register")
    public ResponseEntity<?> registerParticipation(@Valid @RequestBody ParticipationRequest participationRequest) {
        Participation participation = participationVmMapper.toParticipation(participationRequest);

        Participation savedParticipation = participationService.registerParticipant(participation);
        ParticipationResponse participationResponse = participationVmMapper.toParticipationResponse(savedParticipation);
        return ResponseEntity.ok(participationResponse);
    }
}
