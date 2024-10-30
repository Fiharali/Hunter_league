package com.ali.hunter.web.vm.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParticipationRequest {
    @NotNull(message = "User ID cannot be null")
    private UUID userId;

    @NotNull(message = "Competition ID cannot be null")
    private UUID competitionId;
}
