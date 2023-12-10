package com.app.fishcompetition.mapper;

import com.app.fishcompetition.model.dto.LevelDto;
import com.app.fishcompetition.model.entity.Level;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class LevelDtoConverter {

    private final ModelMapper modelMapper;
    public Level convertDtoTOLevel(LevelDto levelDto) {
        Level level = modelMapper.map(levelDto, Level.class);
        return level;
    }
    public LevelDto convertLevelTODto(Level level) {
        LevelDto levelDto = modelMapper.map(level, LevelDto.class);
        return levelDto;
    }
}
