package com.ali.hunter.service;

import com.ali.hunter.domain.entity.Species;
import com.ali.hunter.exception.DuplicateResourceException;
import com.ali.hunter.exception.ResourceNotFoundException;
import com.ali.hunter.repository.SpeciesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SpeciesService {

    private final SpeciesRepository speciesRepository;


    public List<Species> getSpeciesByCategory(Species species) {
        return speciesRepository.findByCategory(species.getCategory());
    }


    public Species addSpecies(Species species) {
        if (speciesRepository.existsByName(species.getName())) {
            throw new DuplicateResourceException("Species with name '" + species.getName() + "' already exists.");
        }
        return speciesRepository.save(species);
    }

    public Species deleteSpeciesById(Species species) {
        Species speciesToDelete = speciesRepository.findById(species.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Species with id '" + species.getId() + "' does not exist."));

        speciesRepository.deleteById(species.getId());
        return speciesToDelete;
    }
}
