package com.app.fishcompetition.controllers;

import com.app.fishcompetition.common.exceptions.custom.DateNotAvailableException;
import com.app.fishcompetition.common.responses.RequestResponseWithDetails;
import com.app.fishcompetition.common.responses.RequestResponseWithoutDetails;
import com.app.fishcompetition.mapper.CompetitionDtoConverter;
import com.app.fishcompetition.model.dto.CompetitionDto;
import com.app.fishcompetition.model.entity.Competition;
import com.app.fishcompetition.services.CompetitionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CompetitionController {

    private final CompetitionService competitionService;
    private final RequestResponseWithoutDetails requestResponseWithoutDetails;
    private final RequestResponseWithDetails  requestResponseWithDetails ;
    private final CompetitionDtoConverter competitionDtoConverter;
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
}
