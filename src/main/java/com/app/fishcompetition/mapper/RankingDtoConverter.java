package com.app.fishcompetition.mapper;

import com.app.fishcompetition.model.dto.RankingDto;
import com.app.fishcompetition.model.entity.Ranking;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RankingDtoConverter {

    private final ModelMapper modelMapper;

    public RankingDto convertToDto(Ranking ranking){
        return modelMapper.map(ranking,RankingDto.class);
    }
    public Ranking convertToEntity(RankingDto rankingDto){
        return modelMapper.map(rankingDto,Ranking.class);
    }
}
