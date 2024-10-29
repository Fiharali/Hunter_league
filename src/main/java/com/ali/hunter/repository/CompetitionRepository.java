package com.ali.hunter.repository;

import com.ali.hunter.domain.entity.Competition;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface CompetitionRepository extends JpaRepository<Competition, UUID> {
}
