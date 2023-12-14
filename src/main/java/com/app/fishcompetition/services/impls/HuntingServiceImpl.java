package com.app.fishcompetition.services.impls;

import com.app.fishcompetition.common.exceptions.custom.AverageWeightException;
import com.app.fishcompetition.common.exceptions.custom.HuntingAllReadyExistException;
import com.app.fishcompetition.model.entity.Hunting;
import com.app.fishcompetition.repositories.HuntingRepository;
import com.app.fishcompetition.services.CompetitionService;
import com.app.fishcompetition.services.FishService;
import com.app.fishcompetition.services.HuntingService;
import com.app.fishcompetition.services.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    @Override
    public List<Hunting> getAllHunting() {
        return null;
    }

    @Override
    public Optional<Hunting> getHuntingById(UUID huntingId) {
        return  huntingRepository.findById(huntingId);
    }

    @Override
    public Hunting addHunting(Hunting hunting, double weight) {

        if(!checkIfMemberExist(hunting.getMember().getId())) {
            throw new NoSuchElementException("Member does that you entered not exist");
        } else if(!checkIfFishExist(hunting.getFish().getId())){
            throw new NoSuchElementException("Fish that you entered not exist");
        }else if(!checkIfCompetitionExist(hunting.getCompetition().getId())){
            throw new NoSuchElementException("Competition that you entered not exist");
        }else {
            if(weight < getFishAverageWeight(hunting.getFish().getId())){
                throw new AverageWeightException("Weight that you entered is less than average weight of fish");
            } else {
                Optional<Hunting> huntingOptional = getHuntingByMemberIdAndFishIdAndCompetitionId(hunting.getMember().getId(),hunting.getFish().getId(),hunting.getCompetition().getId());
                if(huntingOptional.isPresent()){
                    Hunting existingHunting = huntingOptional.get();
                    existingHunting.setNumberOfFish(existingHunting.getNumberOfFish()+1);
                    return huntingRepository.save(existingHunting);
                } else {
                    hunting.setNumberOfFish(1);
                    return huntingRepository.save(hunting);
                }
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
    public boolean checkIfHuntingForSameMemberAndSameFishAndSameCompetition(UUID memberId, UUID fishId,UUID competitionId){
            return getHuntingByMemberIdAndFishIdAndCompetitionId(memberId,fishId,competitionId).isPresent();
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
}
