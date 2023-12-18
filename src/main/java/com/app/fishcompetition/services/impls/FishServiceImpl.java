package com.app.fishcompetition.services.impls;

import com.app.fishcompetition.model.entity.Fish;
import com.app.fishcompetition.model.entity.Level;
import com.app.fishcompetition.repositories.FishRepository;
import com.app.fishcompetition.services.FishService;
import com.app.fishcompetition.services.LevelService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class FishServiceImpl implements FishService {

    private final FishRepository fishRepository;
    private final LevelService levelService;
    @Override
    public List<Fish> getAllFish() {
        return fishRepository.findAll();
    }

    @Override
    public Page<Fish> getAllFishWithPagination(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber,pageSize);
        return fishRepository.findAll(pageable);
    }
    @Override
    public Optional<Fish> getFishById(UUID fishId) {
        return fishRepository.findById(fishId);
    }

    @Override
    public Fish addFish(Fish fish) {;
        Optional<Level> levelById = levelService.getLevelById(fish.getLevel().getId()); ;
        if (levelById.isEmpty()) {
            throw new NoSuchElementException("Level with id: "+fish.getLevel().getId()+" not found");
        }
        return fishRepository.save(fish);
    }

    @Override
    public Fish updateFish(UUID fishId, Fish fish) {
        return null;
    }

    @Override
    public void deleteFish(UUID fishId) {
        Optional<Fish> fishToDelete = getFishById(fishId);
        if(fishToDelete.isPresent()) {
            fishRepository.delete(fishToDelete.get());
        } else {
            throw new NoSuchElementException("fish with id " + fishId + " does not exist");
        }
    }
}
