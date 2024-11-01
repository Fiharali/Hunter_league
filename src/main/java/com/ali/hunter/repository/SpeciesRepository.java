package com.ali.hunter.repository;

import com.ali.hunter.domain.entity.Species;
import com.ali.hunter.domain.enums.SpeciesType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface SpeciesRepository extends JpaRepository<Species, UUID> {

    Page<Species> findByCategory(SpeciesType category, Pageable pageable);


    Page<Species> findAll(Pageable pageable);

    boolean existsByName(String name);
}
