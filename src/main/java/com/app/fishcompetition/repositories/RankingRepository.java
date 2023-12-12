package com.app.fishcompetition.repositories;

import com.app.fishcompetition.model.entity.Ranking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RankingRepository extends JpaRepository<Ranking, UUID> {

    @Query("SELECT r FROM Ranking r WHERE r.member.id = ?1 AND r.competition.id = ?2")
    Optional<Ranking> findByMemberIdAndCompetitionId(UUID memberId, UUID competitionId);;
}
