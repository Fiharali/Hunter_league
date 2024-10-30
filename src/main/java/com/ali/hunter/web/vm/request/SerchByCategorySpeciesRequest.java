package com.ali.hunter.web.vm.request;

import com.ali.hunter.domain.enums.SpeciesType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SerchByCategorySpeciesRequest {
    @Enumerated(EnumType.STRING)
    private SpeciesType category;
}
