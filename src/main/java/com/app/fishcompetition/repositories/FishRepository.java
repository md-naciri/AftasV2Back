package com.app.fishcompetition.repositories;

import com.app.fishcompetition.model.entity.Fish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface FishRepository extends JpaRepository<Fish, UUID> {
}
