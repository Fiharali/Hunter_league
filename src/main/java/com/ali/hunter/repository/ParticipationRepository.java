package com.ali.hunter.repository;

import com.ali.hunter.domain.entity.Competition;
import com.ali.hunter.domain.entity.Participation;
import com.ali.hunter.domain.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ParticipationRepository extends JpaRepository<Participation, UUID> {
    Participation findByCompetitionId(UUID id);

    Integer countByCompetitionId(UUID id);

    void deleteParticipationsByUser(User userToDelete);

    List<Participation> findByUser(User userToDelete, Pageable pageable);

    @Procedure(name = "DeleteParticipationWithHunts")
    void deleteParticipationWithHunts(@Param("id") UUID id);

    @Modifying
    @Query("DELETE FROM Participation p WHERE p.user = :user")
    void deleteByUser(@Param("user") User user);

    List<Participation> findByUserAndCompetition(User user, Competition competition);

    List<Participation> findTop3ByCompetitionOrderByScoreDesc(Competition competition);


    Page<Participation> findParticipationByUser(User user, Pageable pageable);

    List<Participation> findByCompetitionOrderByScoreDesc(Competition competition);
}
