package com.ali.hunter.repository;

import com.ali.hunter.domain.entity.Species;
import com.ali.hunter.domain.enums.SpeciesType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface SpeciesRepository extends JpaRepository<Species, UUID> {

    List<Species> findByCategory(SpeciesType category);

    boolean existsByName(String name);
}
