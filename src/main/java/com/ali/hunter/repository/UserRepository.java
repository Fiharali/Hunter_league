package com.ali.hunter.repository;

import com.ali.hunter.domain.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    Page<User> findByCinContainingIgnoreCaseOrEmailContainingIgnoreCaseOrFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(
            String cin, String email, String firstName, String lastName, Pageable pageable);



    Page<User> findAll(Pageable pageable);

    Optional<User> findByEmail(String email);

    @Procedure(procedureName = "delete_user_with_related_data3")
    void deleteUserWithRelatedData(UUID id);
}
