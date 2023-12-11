package com.app.fishcompetition.mapper;

import com.app.fishcompetition.model.dto.CompetitionDto;
import com.app.fishcompetition.model.entity.Competition;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CompetitionDtoConverter {
    private final ModelMapper modelMapper;

    public Competition convertDtoTOCompetition(CompetitionDto competitionDto) {
        Competition competition = modelMapper.map(competitionDto, Competition.class);
        return competition;
    }
    public CompetitionDto convertCompetitionTODto(Competition competition) {
        CompetitionDto competitionDto = modelMapper.map(competition, CompetitionDto.class);
        return competitionDto;
    }
}
