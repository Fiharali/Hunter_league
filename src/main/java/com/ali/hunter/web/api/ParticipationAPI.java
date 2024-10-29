package com.ali.hunter.web.api;

import com.ali.hunter.domain.entity.Participation;
import com.ali.hunter.service.ParticipationService;
import com.ali.hunter.web.vm.ParticipationVM;
import com.ali.hunter.web.vm.mapper.ParticipationMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/participations")
public class ParticipationAPI {

    private final ParticipationService participationService;
    private final ParticipationMapper participationMapper;

    public ParticipationAPI(ParticipationService participationService, ParticipationMapper participationMapper) {
        this.participationService = participationService;
        this.participationMapper = participationMapper;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerParticipation(@Valid @RequestBody ParticipationVM participationVM) {
        Participation participation = participationMapper.toParticipation(participationVM);
        Participation savedParticipation = participationService.registerParticipant(participation);
        return ResponseEntity.ok(savedParticipation);
    }
}
