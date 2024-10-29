package com.ali.hunter.service;

import com.ali.hunter.domain.entity.Species;
import com.ali.hunter.repository.SpeciesRepository;
import com.ali.hunter.web.vm.SerchByCategorySpeciesVM;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SpeciesService {

    private final SpeciesRepository speciesRepository;


    public List<Species> getSpeciesByCategory(SerchByCategorySpeciesVM serchByCategorySpeciesVM) {
        return speciesRepository.findByCategory(serchByCategorySpeciesVM.getCategory());
    }
}
