package com.app.fishcompetition.services.impls;

import com.app.fishcompetition.common.exceptions.GlobalExceptionHandler;
import com.app.fishcompetition.model.entity.Level;
import com.app.fishcompetition.repositories.LevelRepository;
import com.app.fishcompetition.services.LevelService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
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
        return levelRepository.findById(levelId);
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
       Optional<Level> levelToDelete = getLevelById(levelId);
         if(levelToDelete.isPresent()) {
           levelRepository.delete(levelToDelete.get());
         } else {
             throw new NoSuchElementException("Level with id " + levelId + " does not exist");
         }
    }
}
