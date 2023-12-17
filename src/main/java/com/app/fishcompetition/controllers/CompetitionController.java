package com.app.fishcompetition.controllers;

import com.app.fishcompetition.common.exceptions.custom.DateNotAvailableException;
import com.app.fishcompetition.common.responses.RequestResponseWithDetails;
import com.app.fishcompetition.common.responses.RequestResponseWithoutDetails;
import com.app.fishcompetition.mapper.CompetitionDtoConverter;
import com.app.fishcompetition.model.dto.CompetitionDto;
import com.app.fishcompetition.model.entity.Competition;
import com.app.fishcompetition.services.CompetitionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CompetitionController {

    private final CompetitionService competitionService;
    private final RequestResponseWithoutDetails requestResponseWithoutDetails;
    private final RequestResponseWithDetails  requestResponseWithDetails ;
    private final CompetitionDtoConverter competitionDtoConverter;
    private ObjectMapper mapper;
    @PostMapping("/competition")
    public ResponseEntity<RequestResponseWithDetails> addCompetition(@Valid @RequestBody CompetitionDto competitionDto)  {

        Map<String,Object> response = new HashMap<>();
        Competition competition = competitionService.addCompetition(competitionDtoConverter.convertDtoTOCompetition(competitionDto));
        requestResponseWithDetails.setTimestamp(LocalDateTime.now());
        requestResponseWithDetails.setMessage("competition added successfully");
        requestResponseWithDetails.setStatus("200");
        response.put("Competition",competition);
        requestResponseWithDetails.setDetails(response);
        return ResponseEntity.ok().body(requestResponseWithDetails);

    }
    @GetMapping("/competitions")
    public ResponseEntity<RequestResponseWithDetails> getAllCompetitions()  {

        Map<String,Object> response = new HashMap<>();
        List<Competition> competitions = competitionService.getAllCompetitions();
        List<CompetitionDto> competitionDtoList  = new ArrayList<>();
        for(Competition competition: competitions){
            competitionDtoList.add(competitionDtoConverter.convertCompetitionTODto(competition));
        }
        requestResponseWithDetails.setTimestamp(LocalDateTime.now());
        requestResponseWithDetails.setMessage("competitions retrieved successfully");
        requestResponseWithDetails.setStatus("200");
        response.put("Competitions",competitionDtoList);
        requestResponseWithDetails.setDetails(response);
        return ResponseEntity.ok().body(requestResponseWithDetails);
    }
}
