package com.app.fishcompetition.services;

import com.app.fishcompetition.model.entity.Level;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LevelService {

    List<Level> getAllLevels();

    Page<Level> getAllLevelsWithPagination(int pageNumber, int pageSize);
    Optional<Level> getLevelById(UUID levelId);

    Level addLevel(Level level);

    Level updateLevel(UUID levelId, Level level);

    void deleteLevel(UUID levelId);
}
