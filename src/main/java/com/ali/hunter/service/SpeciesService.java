package com.ali.hunter.service;

import com.ali.hunter.domain.entity.Species;
import com.ali.hunter.exception.DuplicateResourceException;
import com.ali.hunter.exception.ResourceNotFoundException;
import com.ali.hunter.repository.SpeciesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

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

    public Species updateSpecies(UUID id, Species updatedSpecies) {
        Species species = speciesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Species with id '" + id + "' does not exist."));

        species.setName(updatedSpecies.getName());
        species.setCategory(updatedSpecies.getCategory());
        species.setMinimumWeight(updatedSpecies.getMinimumWeight());
        species.setDifficulty(updatedSpecies.getDifficulty());
        species.setPoints(updatedSpecies.getPoints());

        return speciesRepository.save(species);
    }
}
