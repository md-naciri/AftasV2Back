package com.app.fishcompetition.repositories;

import com.app.fishcompetition.model.entity.Competition;
import com.app.fishcompetition.model.entity.Level;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface LevelRepository extends JpaRepository<Level, UUID> {
    @Query("SELECT l FROM Level l WHERE l.level = ?1")
    Level findByLevel(int level);
}
