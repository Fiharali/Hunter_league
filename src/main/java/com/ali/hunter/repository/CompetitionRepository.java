package com.ali.hunter.repository;

import com.ali.hunter.domain.entity.Competition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CompetitionRepository extends JpaRepository<Competition, UUID> {

    Page<Competition> findAll(Pageable pageable);
}
