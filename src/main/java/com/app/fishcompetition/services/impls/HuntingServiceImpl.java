package com.app.fishcompetition.services.impls;

import com.app.fishcompetition.model.entity.Hunting;
import com.app.fishcompetition.services.HuntingService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class HuntingServiceImpl implements HuntingService {
    @Override
    public List<Hunting> getAllHunting() {
        return null;
    }

    @Override
    public Optional<Hunting> getHuntingById(UUID huntingId) {
        return Optional.empty();
    }

    @Override
    public Hunting addHunting(Hunting hunting) {
        return null;
    }

    @Override
    public Hunting updateHunting(UUID huntingId, Hunting hunting) {
        return null;
    }

    @Override
    public void deleteHunting(UUID huntingId) {

    }
}
