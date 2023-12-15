package com.app.fishcompetition.services.impls;

import com.app.fishcompetition.common.exceptions.custom.AverageWeightException;
import com.app.fishcompetition.common.exceptions.custom.CompetitionTimeException;
import com.app.fishcompetition.common.exceptions.custom.HuntingAllReadyExistException;
import com.app.fishcompetition.model.entity.Competition;
import com.app.fishcompetition.model.entity.Fish;
import com.app.fishcompetition.model.entity.Hunting;
import com.app.fishcompetition.model.entity.Ranking;
import com.app.fishcompetition.repositories.HuntingRepository;
import com.app.fishcompetition.services.CompetitionService;
import com.app.fishcompetition.services.FishService;
import com.app.fishcompetition.services.HuntingService;
import com.app.fishcompetition.services.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class HuntingServiceImpl implements HuntingService {

    private final HuntingRepository huntingRepository;
    private final MemberService memberService;
    private final FishService fishService;
    private final CompetitionService competitionService;
    private final RankingServiceImpl rankingService;
    @Override
    public List<Hunting> getAllHunting() {
         return  huntingRepository.findAll();
    }

    @Override
    public Optional<Hunting> getHuntingById(UUID huntingId) {
        return  huntingRepository.findById(huntingId);
    }

    @Override
    public Hunting addHunting(Hunting hunting, double weight) {
        if(!checkIfMemberExist(hunting.getMember().getId())) {
            throw new NoSuchElementException("Member  that you entered not exist");
        } else if(!checkIfFishExist(hunting.getFish().getId())){
            throw new NoSuchElementException("Fish that you entered not exist");
        }else if(!checkIfCompetitionExist(hunting.getCompetition().getId())){
            throw new NoSuchElementException("Competition that you entered not exist");
        }else if(isCurrentTimeAfterCompetitionTime(getCompetitionById(hunting.getCompetition().getId()).getEndTime().toLocalTime())){
            throw new CompetitionTimeException("Competition time is over");
        }else if(isCurrentTimeBeforeCompetitionTime(getCompetitionById(hunting.getCompetition().getId()).getStartTime().toLocalTime())){
            throw new CompetitionTimeException("Competition is not started yet");
        }else if(checkIfMemberAlreadyRanked(hunting.getMember().getId(),hunting.getCompetition().getId())){
            throw new HuntingAllReadyExistException("Member that you entered not belong to this competition");
        }else {
            if(weight < getFishAverageWeight(hunting.getFish().getId())){
                throw new AverageWeightException("Weight that you entered is less than average weight of fish");
            } else {
                Optional<Hunting> huntingOptional = getHuntingByMemberIdAndFishIdAndCompetitionId(hunting.getMember().getId(),hunting.getFish().getId(),hunting.getCompetition().getId());

                Hunting savedHunting;

                if(huntingOptional.isPresent()){
                    Hunting existingHunting = huntingOptional.get();
                    existingHunting.setNumberOfFish(existingHunting.getNumberOfFish()+1);
                    savedHunting = huntingRepository.save(existingHunting);
                } else {
                    hunting.setNumberOfFish(1);
                    UUID fishId = hunting.getFish().getId();
                    Fish fish = fishService.getFishById(fishId).orElseThrow(() -> new NoSuchElementException("Fish that you entered does not exist"));
                    hunting.setFish(fish);
                    savedHunting = huntingRepository.save(hunting);
                }

                Optional<Ranking> ranking = rankingService.getRankingByMemberIdAndCompetitionId(savedHunting.getMember().getId(),savedHunting.getCompetition().getId());
                if(!ranking.isPresent()){
                    throw new NoSuchElementException("Ranking that you want update not exist");
                }else{
                    ranking.get().setScore(calculateScoreOfMember(savedHunting.getMember().getId(),savedHunting.getCompetition().getId()));
                    rankingService.updateRanking(ranking.get().getId(),ranking.get());
                }

                return savedHunting;
            }
        }
    }

    @Override
    public Hunting updateHunting(UUID huntingId, Hunting hunting) {
        return  huntingRepository.save(hunting);
    }

    @Override
    public void deleteHunting(UUID huntingId) {

    }

    public Optional<Hunting> getHuntingByMemberIdAndFishIdAndCompetitionId(UUID memberId, UUID fishId,UUID competitionId){
        return huntingRepository.findByMemberIdAndFishId(memberId,fishId,competitionId);
    }
    public boolean checkIfMemberExist(UUID memberId){
        return memberService.getMemberById(memberId).isPresent();
    }
    public boolean checkIfFishExist(UUID fishId){
        return fishService.getFishById(fishId).isPresent();
    }
    public boolean checkIfCompetitionExist(UUID competitionId){
        return competitionService.getCompetitionById(competitionId).isPresent();
    }
    public double getFishAverageWeight(UUID fishId){
        return fishService.getFishById(fishId).get().getAverageWeight();
    }


   public int calculateScoreOfMember(UUID memberId, UUID competitionId){
       int score = 0;
         if(!checkIfMemberExist(memberId)){
             throw new NoSuchElementException("Member does that you entered not exist");
         }else  if(!checkIfCompetitionExist(competitionId)){
             throw new NoSuchElementException("Competition that you entered not exist");
         }else{
             List<Hunting> allHunting = getAllHuntingOfMemberInCompetition(memberId,competitionId);

             for(Hunting hunting : allHunting){

                     score += hunting.getNumberOfFish() * hunting.getFish().getLevel().getPoints();
             }
         }
      return score;
   }
   public List<Hunting> getAllHuntingOfMemberInCompetition(UUID memberId, UUID competitionId){
      return huntingRepository.getAllHuntingOfSameCompetitionAndSameMember(memberId,competitionId);
   }
   public boolean checkIfMemberAlreadyRanked(UUID memberId, UUID competitionId){
       return rankingService.checkIfUserAlreadyRankedWithSameCompetition(memberId,competitionId);
   }
    public boolean isCurrentTimeAfterCompetitionTime(LocalTime competitionEndTime) {
        LocalTime now = LocalTime.now();
        return now.isAfter(competitionEndTime);
    }
    public boolean isCurrentTimeBeforeCompetitionTime(LocalTime competitionStartTime){
        LocalTime now = LocalTime.now();
        return  now.isBefore(competitionStartTime);
    }
    public Competition getCompetitionById(UUID competitionId){
        return competitionService.getCompetitionById(competitionId).orElseThrow(() -> new NoSuchElementException("Competition that you entered does not exist"));
    }

}
