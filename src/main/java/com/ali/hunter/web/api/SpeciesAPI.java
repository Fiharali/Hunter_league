package com.ali.hunter.web.api;

import com.ali.hunter.domain.entity.Species;
import com.ali.hunter.dto.SpeciesDTO;
import com.ali.hunter.dto.mapper.SpeciesMapper;
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
public class SpeciesAPI {

    private final SpeciesService speciesService;
    private final SpeciesMapper speciesMapper;

    @GetMapping
    public ResponseEntity<List<SpeciesDTO>> getSpecies(@Valid SerchByCategorySpeciesVM serchByCategorySpeciesVM) {

        List<Species> species =  speciesService.getSpeciesByCategory(serchByCategorySpeciesVM);
        List<SpeciesDTO> speciesDTOList = speciesMapper.toSpeciesDTOList(species);
        
        return ResponseEntity.ok(speciesDTOList);
    }
}
