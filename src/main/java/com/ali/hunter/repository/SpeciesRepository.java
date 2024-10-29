package com.ali.hunter.repository;

import com.ali.hunter.domain.entity.Species;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface SpeciesRepository extends JpaRepository<Species, UUID> {
}
