package com.ali.hunter.repository;


import com.ali.hunter.domain.entity.Hunt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public interface HuntRepository extends JpaRepository<Hunt, UUID> {
    @Transactional
    void deleteBySpeciesId(UUID id);
}
