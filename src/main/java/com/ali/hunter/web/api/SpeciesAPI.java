package com.ali.hunter.web.api;

import com.ali.hunter.domain.entity.Species;
import com.ali.hunter.dto.SpeciesDTO;
import com.ali.hunter.dto.mapper.SpeciesMapper;
import com.ali.hunter.service.SpeciesService;
import com.ali.hunter.web.vm.SerchByCategorySpeciesVM;
import com.ali.hunter.web.vm.mapper.SpeciesVmMapper;
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
    private final SpeciesVmMapper speciesVmMapper;

    @GetMapping
    public ResponseEntity<List<SpeciesDTO>> getSpecies(@Valid SerchByCategorySpeciesVM serchByCategorySpeciesVM) {

        Species speciesEntity = speciesVmMapper.toSpecies(serchByCategorySpeciesVM);

        List<Species> species =  speciesService.getSpeciesByCategory(speciesEntity);

        List<SpeciesDTO> speciesDTOList = speciesMapper.toSpeciesDTOList(species);
        
        return ResponseEntity.ok(speciesDTOList);
    }
}
