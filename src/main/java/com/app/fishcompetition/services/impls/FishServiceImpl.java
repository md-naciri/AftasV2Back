package com.app.fishcompetition.services.impls;

import com.app.fishcompetition.model.entity.Fish;
import com.app.fishcompetition.services.FishService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FishServiceImpl implements FishService {
    @Override
    public List<Fish> getAllFish() {
        return null;
    }

    @Override
    public Optional<Fish> getFishById(UUID fishId) {
        return Optional.empty();
    }

    @Override
    public Fish addFish(Fish fish) {
        return null;
    }

    @Override
    public Fish updateFish(UUID fishId, Fish fish) {
        return null;
    }

    @Override
    public void deleteFish(UUID fishId) {

    }
}
