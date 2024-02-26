package com.app.fishcompetition.services;

import com.app.fishcompetition.model.entity.Ranking;
import com.app.fishcompetition.model.entity.RankingKey;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RankingService {
    List<Ranking> getRankings();
    Optional<Ranking>   getRankingById(RankingKey id);

    Ranking addRanking(Ranking ranking);

    void updateRanking(RankingKey id, Ranking ranking);

    void deleteRanking(UUID id);

    List<Ranking> getRankingsByCompetitionId(UUID competitionId);
}
