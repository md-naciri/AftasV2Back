package com.app.fishcompetition.services.impls;

import com.app.fishcompetition.model.entity.Hunting;
import com.app.fishcompetition.repositories.HuntingRepository;
import com.app.fishcompetition.services.HuntingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class HuntingServiceImpl implements HuntingService {

    private final HuntingRepository huntingRepository;
    @Override
    public List<Hunting> getAllHunting() {
        return null;
    }

    @Override
    public Optional<Hunting> getHuntingById(UUID huntingId) {
        return  huntingRepository.findById(huntingId);
    }

    @Override
    public Hunting addHunting(Hunting hunting) {
        if(checkIfHuntingForTheSameMemberAndSameFish(hunting.getMember().getId(),hunting.getFish().getId())){
            throw new RuntimeException("Hunting for the same member and same fish already exists");
        }
        return huntingRepository.save(hunting);
    }

    @Override
    public Hunting updateHunting(UUID huntingId, Hunting hunting) {
        return null;
    }

    @Override
    public void deleteHunting(UUID huntingId) {

    }
    public boolean checkIfHuntingForTheSameMemberAndSameFish(UUID memberId, UUID fishId){
            return getHuntingByMemberIdAndFishId(memberId,fishId).isPresent();
    }
    public Optional<Hunting> getHuntingByMemberIdAndFishId(UUID memberId, UUID fishId){
        return huntingRepository.findByMemberIdAndFishId(memberId,fishId);
    }
}
