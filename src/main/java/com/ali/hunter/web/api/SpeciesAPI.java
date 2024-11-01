package com.ali.hunter.web.api;

import com.ali.hunter.domain.entity.Species;
import com.ali.hunter.web.vm.request.SpeciesRequest;

import com.ali.hunter.web.vm.response.SpeciesResponse;
import com.ali.hunter.service.SpeciesService;
import com.ali.hunter.web.vm.request.SerchByCategorySpeciesRequest;
import com.ali.hunter.web.vm.mapper.SpeciesMapperVm;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/species")
@RequiredArgsConstructor
public class SpeciesAPI {

    private final SpeciesService speciesService;
    private final SpeciesMapperVm speciesMapperVm;

    @GetMapping
    public ResponseEntity<List<SpeciesResponse>> getSpecies(@Valid SerchByCategorySpeciesRequest serchByCategorySpeciesRequest,
                                                            @RequestParam(defaultValue = "0") int page,
                                                            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("points").ascending());

        Species speciesEntity = speciesMapperVm.toSpecies(serchByCategorySpeciesRequest);
        Page<Species> species =  speciesService.getSpeciesByCategory(speciesEntity,pageable);
        List<SpeciesResponse> speciesResponseList = speciesMapperVm.toSpeciesResponseList(species);
        return ResponseEntity.ok(speciesResponseList);
    }

    @PostMapping
    public ResponseEntity<SpeciesResponse> addSpecies(
            @Valid @RequestBody SpeciesRequest speciesRequest) {
        Species speciesEntity = speciesMapperVm.toSpecies(speciesRequest);
        Species species = speciesService.addSpecies(speciesEntity);
        return ResponseEntity.ok(speciesMapperVm.toSpeciesResponse(species));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SpeciesResponse> deleteSpeciesById(@PathVariable UUID id) {
        Species species = new Species();
        species.setId(id);
        Species deletedspecies = speciesService.deleteSpeciesById(species);
        return ResponseEntity.ok(speciesMapperVm.toSpeciesResponse(deletedspecies));
    }


    @PutMapping("/{id}")
    public ResponseEntity<SpeciesResponse> updateSpecies(
            @PathVariable UUID id,
            @Valid @RequestBody SpeciesRequest speciesRequest) {

        Species updatedSpecies = speciesMapperVm.toSpecies(speciesRequest);

        Species savedSpecies = speciesService.updateSpecies(id, updatedSpecies);

        SpeciesResponse speciesResponse = speciesMapperVm.toSpeciesResponse(savedSpecies);
        return ResponseEntity.ok(speciesResponse);
    }

}
