package com.app.fishcompetition.services;

import com.app.fishcompetition.model.entity.Hunting;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface HuntingService {

    List<Hunting> getAllHunting();

    Optional<Hunting> getHuntingById(UUID huntingId);

    Hunting addHunting(Hunting hunting);

    Hunting updateHunting(UUID huntingId, Hunting hunting);

    void deleteHunting(UUID huntingId);
}
