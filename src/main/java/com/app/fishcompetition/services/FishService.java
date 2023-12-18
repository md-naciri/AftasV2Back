package com.app.fishcompetition.services;

import com.app.fishcompetition.model.entity.Fish;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FishService {

    List<Fish> getAllFish();

    Page<Fish> getAllFishWithPagination(int pageNumber, int pageSize);
    Optional<Fish> getFishById(UUID fishId);

    Fish addFish(Fish fish);

    Fish updateFish(UUID fishId, Fish fish);

    void deleteFish(UUID fishId);
}
