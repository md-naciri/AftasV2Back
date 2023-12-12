package com.app.fishcompetition.services.impls;

import com.app.fishcompetition.model.entity.Ranking;
import com.app.fishcompetition.services.RankingService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class RankingServiceImpl  implements RankingService {

    @Override
    public List<Ranking> getRankings() {
        return null;
    }

    @Override
    public Optional<Ranking> getRankingById(UUID id) {
        return Optional.empty();
    }

    @Override
    public Ranking addRanking(Ranking ranking) {
        return null;
    }

    @Override
    public Ranking updateRanking(UUID id, Ranking ranking) {
        return null;
    }

    @Override
    public void deleteRanking(UUID id) {

    }
}
