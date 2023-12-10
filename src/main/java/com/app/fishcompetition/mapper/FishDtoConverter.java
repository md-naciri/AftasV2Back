package com.app.fishcompetition.mapper;

import com.app.fishcompetition.model.dto.FishDto;
import com.app.fishcompetition.model.entity.Fish;
import com.app.fishcompetition.model.entity.Level;
import com.app.fishcompetition.repositories.LevelRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class FishDtoConverter {

    private final ModelMapper modelMapper;
    public FishDto convertFishTODto(Fish fish) {
        FishDto fishDto = modelMapper.map(fish, FishDto.class);
        return fishDto;
    }
    public Fish convertDtoToFish(FishDto fishDto) {
        Fish fish = modelMapper.map(fishDto, Fish.class);
        return fish;
    }

}
