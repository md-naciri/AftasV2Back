package com.app.fishcompetition.services;

import com.app.fishcompetition.common.exceptions.custom.DateNotAvailableException;
import com.app.fishcompetition.model.entity.Competition;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface CompetitionService {
    List<Competition> getAllCompetitions();

    Optional<Competition> getCompetitionById(UUID competitionId);

    Competition addCompetition(Competition competition) ;

    Competition updateCompetition(UUID competitionId, Competition competition);

    void deleteCompetition(UUID competitionId);
}
