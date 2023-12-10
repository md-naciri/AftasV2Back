package com.app.fishcompetition.mapper;

import com.app.fishcompetition.model.dto.FishDto;
import com.app.fishcompetition.model.entity.Fish;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
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
