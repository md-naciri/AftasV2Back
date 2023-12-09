package com.app.fishcompetition.services.impls;

import com.app.fishcompetition.model.entity.Competition;
import com.app.fishcompetition.services.CompetitionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CompetitionServiceImpl implements CompetitionService {
    @Override
    public List<Competition> getAllCompetitions() {
        return null;
    }

    @Override
    public Optional<Competition> getCompetitionById(UUID competitionId) {
        return Optional.empty();
    }

    @Override
    public Competition addCompetition(Competition competition) {
        return null;
    }

    @Override
    public Competition updateCompetition(UUID competitionId, Competition competition) {
        return null;
    }

    @Override
    public void deleteCompetition(UUID competitionId) {

    }
}
