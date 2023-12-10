package com.app.fishcompetition.services.impls;

import com.app.fishcompetition.model.entity.Level;
import com.app.fishcompetition.repositories.LevelRepository;
import com.app.fishcompetition.services.LevelService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class LevelServiceImpl implements LevelService {

    private final LevelRepository levelRepository;
    @Override
    public List<Level> getAllLevels() {
        return null;
    }

    @Override
    public Optional<Level> getLevelById(UUID levelId) {
        return Optional.empty();
    }

    @Override
    public Level addLevel(Level level) {
        return levelRepository.save(level);
    }

    @Override
    public Level updateLevel(UUID levelId, Level level) {
        return null;
    }

    @Override
    public void deleteLevel(UUID levelId) {

    }
}
