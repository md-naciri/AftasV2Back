package com.app.fishcompetition.repositories;

import com.app.fishcompetition.model.entity.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<AppRole, UUID> {
    Optional<AppRole> findByAuthority(String name);
}
