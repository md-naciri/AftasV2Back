package com.app.fishcompetition.services;

import com.app.fishcompetition.model.entity.Level;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LevelService {

    List<Level> getAllLevels();

    Optional<Level> getLevelById(UUID levelId);

    Level addLevel(Level level);

    Level updateLevel(UUID levelId, Level level);

    void deleteLevel(UUID levelId);
}
