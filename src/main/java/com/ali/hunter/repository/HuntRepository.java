package com.ali.hunter.repository;


import com.ali.hunter.domain.entity.Hunt;
import com.ali.hunter.domain.entity.Participation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public interface HuntRepository extends JpaRepository<Hunt, UUID> {
    @Transactional
    @Modifying
    @Query("DELETE FROM Hunt h WHERE h.species.id = :speciesId")
    void deleteBySpeciesId(@Param("speciesId") UUID speciesId);

    void deleteHuntsByParticipation(Participation participation);
}
