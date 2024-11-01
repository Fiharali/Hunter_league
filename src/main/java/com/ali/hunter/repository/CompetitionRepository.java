package com.ali.hunter.repository;

import com.ali.hunter.domain.entity.Competition;
import com.ali.hunter.repository.dto.CompetitionRepoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface CompetitionRepository extends JpaRepository<Competition, UUID> {

    Page<Competition> findAll(Pageable pageable);

    @Query("SELECT new com.ali.hunter.repository.dto.CompetitionRepoDTO(" +
            "c.id, c.location, c.date, SIZE(c.participations)) " +
            "FROM Competition c")
    Page<CompetitionRepoDTO> findAllRepoDTO(Pageable pageable);

}
