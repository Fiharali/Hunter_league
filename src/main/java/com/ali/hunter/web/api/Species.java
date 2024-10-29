package com.ali.hunter.web.api;

import com.ali.hunter.service.SpeciesService;
import com.ali.hunter.web.vm.SerchByCategorySpeciesVM;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/species")
@RequiredArgsConstructor
public class Species {

    private final SpeciesService speciesService;

    @GetMapping
    public ResponseEntity<List<com.ali.hunter.domain.entity.Species>> getSpecies(@Valid SerchByCategorySpeciesVM serchByCategorySpeciesVM) {

        List<com.ali.hunter.domain.entity.Species> species =  speciesService.getSpeciesByCategory(serchByCategorySpeciesVM);
        return ResponseEntity.ok(species);
    }
}
