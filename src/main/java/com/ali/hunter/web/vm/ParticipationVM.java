package com.ali.hunter.web.vm;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParticipationVM {
    @NotNull(message = "UserAPI ID cannot be null")
    private UUID userId;

    @NotNull(message = "Competition ID cannot be null")
    private UUID competitionId;
}
