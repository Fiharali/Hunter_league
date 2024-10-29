package com.ali.hunter.repository;

import com.ali.hunter.domain.entity.Participation;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface ParticipationRepository extends JpaRepository<Participation, UUID> {
}
