package com.ali.hunter.controller;

import com.ali.hunter.domain.entity.Participation;
import com.ali.hunter.service.ParticipationService;
import com.ali.hunter.service.dto.ParticipationRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/participations")
public class ParticipationController {

    private final ParticipationService participationService;

    public ParticipationController(ParticipationService participationService) {
        this.participationService = participationService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerParticipation(@Valid @RequestBody ParticipationRequestDTO requestDTO) {
        Participation participation = participationService.registerParticipant(requestDTO);
        return ResponseEntity.ok(participation);
    }
}
