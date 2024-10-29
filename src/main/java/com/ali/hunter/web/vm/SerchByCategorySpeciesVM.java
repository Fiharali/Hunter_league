package com.ali.hunter.web.vm;

import com.ali.hunter.domain.enums.SpeciesType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SerchByCategorySpeciesVM {
    @Enumerated(EnumType.STRING)
    private SpeciesType category;
}
