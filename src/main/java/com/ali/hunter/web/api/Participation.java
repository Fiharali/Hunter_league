package com.ali.hunter.web.api;

import com.ali.hunter.service.ParticipationService;
import com.ali.hunter.web.vm.ParticipationVM;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/participations")
public class Participation {

    private final ParticipationService participationService;

    public Participation(ParticipationService participationService) {
        this.participationService = participationService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerParticipation(@Valid @RequestBody ParticipationVM requestDTO) {
        com.ali.hunter.domain.entity.Participation participation = participationService.registerParticipant(requestDTO);
        return ResponseEntity.ok(participation);
    }
}
