package com.app.fishcompetition.services.impls;

import com.app.fishcompetition.model.entity.Competition;
import com.app.fishcompetition.repositories.CompetitionRepository;
import com.app.fishcompetition.services.CompetitionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CompetitionServiceImpl implements CompetitionService {

    private final CompetitionRepository competitionRepository;
    @Override
    public List<Competition> getAllCompetitions() {
        return null;
    }

    @Override
    public Optional<Competition> getCompetitionById(UUID competitionId) {
        return competitionRepository.findById(competitionId);
    }

    @Override
    public Competition addCompetition(Competition competition) {
       if(!checkIfDateIsAvailable(competition.getDate())){
                throw new RuntimeException("Date is not available");
       }
        competition.setNumberOfParticipants(0);
        return competitionRepository.save(competition);
    }

    @Override
    public Competition updateCompetition(UUID competitionId, Competition competition) {
        return null;
    }

    @Override
    public void deleteCompetition(UUID competitionId) {

    }
    public boolean checkIfDateIsAvailable(Date date){
        LocalDate givenDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate currentDate = LocalDate.now();
        long monthsBetween = ChronoUnit.MONTHS.between(currentDate, givenDate);
        return monthsBetween <= 2;
    }

}
