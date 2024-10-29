package com.ali.hunter.web.api;

import com.ali.hunter.domain.entity.Participation;
import com.ali.hunter.dto.ParticipationDTO;
import com.ali.hunter.dto.mapper.ParticipationMapper;
import com.ali.hunter.service.ParticipationService;
import com.ali.hunter.web.vm.ParticipationVM;
import com.ali.hunter.web.vm.mapper.ParticipationVmMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/participations")
public class ParticipationAPI {

    private final ParticipationService participationService;
    private final ParticipationVmMapper participationVmMapper;
    private final ParticipationMapper participationMapper;

    public ParticipationAPI(ParticipationService participationService, ParticipationVmMapper participationVmMapper, ParticipationMapper participationMapper) {
        this.participationService = participationService;
        this.participationVmMapper = participationVmMapper;
        this.participationMapper = participationMapper;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerParticipation(@Valid @RequestBody ParticipationVM participationVM) {
        Participation participation = participationVmMapper.toParticipation(participationVM);

        Participation savedParticipation = participationService.registerParticipant(participation);

        ParticipationDTO participationDTO = participationMapper.toParticipationDTO(savedParticipation);

        return ResponseEntity.ok(participationDTO);
    }
}
