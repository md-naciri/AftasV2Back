package com.app.fishcompetition.services;

import com.app.fishcompetition.model.entity.Fish;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FishService {

    List<Fish> getAllFish();

    Optional<Fish> getFishById(UUID fishId);

    Fish addFish(Fish fish);

    Fish updateFish(UUID fishId, Fish fish);

    void deleteFish(UUID fishId);
}
