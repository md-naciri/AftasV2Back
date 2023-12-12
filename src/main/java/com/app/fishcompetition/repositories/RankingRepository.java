package com.app.fishcompetition.repositories;

import com.app.fishcompetition.model.entity.Ranking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RankingRepository extends JpaRepository<Ranking, UUID> {
}
