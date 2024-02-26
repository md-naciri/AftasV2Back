package com.app.fishcompetition.repositories;

import com.app.fishcompetition.model.entity.AppPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PermissionRepository extends JpaRepository<AppPermission, UUID> {
    Optional<AppPermission> findByName(String name);
}
