package com.app.fishcompetition.controllers;

import com.app.fishcompetition.common.responses.RequestResponseWithDetails;
import com.app.fishcompetition.mapper.CompetitionDtoConverter;
import com.app.fishcompetition.model.dto.CompetitionDto;
import com.app.fishcompetition.model.entity.Competition;
import com.app.fishcompetition.services.CompetitionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CompetitionController {

    private final CompetitionService competitionService;
    private final RequestResponseWithDetails  requestResponseWithDetails ;
    private final CompetitionDtoConverter competitionDtoConverter;
    private ObjectMapper mapper;
    private final ModelMapper modelMapper;

    @PostMapping("/competition")
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    public ResponseEntity<RequestResponseWithDetails> addCompetition(@Valid @RequestBody CompetitionDto competitionDto)  {

        Map<String,Object> response = new HashMap<>();
        CompetitionDto competition = modelMapper.map(competitionService.addCompetition(competitionDtoConverter.convertDtoTOCompetition(competitionDto)), CompetitionDto.class);
        requestResponseWithDetails.setTimestamp(LocalDateTime.now());
        requestResponseWithDetails.setMessage("competition added successfully");
        requestResponseWithDetails.setStatus("200");
        response.put("Competition",competition);
        requestResponseWithDetails.setDetails(response);
        return ResponseEntity.ok().body(requestResponseWithDetails);

    }
    @GetMapping("/competition/{pageNumber}/{pageSize}")
    @PreAuthorize("hasRole('ROLE_ADHERENT')")
    public ResponseEntity<RequestResponseWithDetails> getAllCompetitions(@PathVariable int pageNumber , @PathVariable int pageSize)  {

        Map<String,Object> response = new HashMap<>();

        requestResponseWithDetails.setTimestamp(LocalDateTime.now());
        requestResponseWithDetails.setMessage("competitions retrieved successfully");
        requestResponseWithDetails.setStatus("200");
        response.put("Competitions",competitionService.getAllCompetitionsWithPagination(pageNumber,pageSize).stream()
                .map(competitionDtoConverter::convertCompetitionTODto)
                .collect(Collectors.toList()));
        requestResponseWithDetails.setDetails(response);
        return ResponseEntity.ok().body(requestResponseWithDetails);
    }

    @GetMapping("/competitions")
    @PreAuthorize("hasRole('ROLE_ADHERENT')")
    public ResponseEntity<RequestResponseWithDetails> getAllCompetitions()  {

        List<Competition> competitions = competitionService.getAllCompetitions();
        Map<String,Object> response = new HashMap<>();
        List<CompetitionDto> competitionData = competitions.stream()
                .map(competitionDtoConverter::convertCompetitionTODto)
                .collect(Collectors.toList());
        response.put("Competitions", competitionData);

        requestResponseWithDetails.setTimestamp(LocalDateTime.now());
        requestResponseWithDetails.setMessage("competitions retrieved successfully");
        requestResponseWithDetails.setStatus("200");
        requestResponseWithDetails.setDetails(response);
        return ResponseEntity.ok().body(requestResponseWithDetails);
    }

    @GetMapping("/competition/{status}")
    @PreAuthorize("hasRole('ROLE_ADHERENT')")
    public ResponseEntity<RequestResponseWithDetails> getCompetitionByStatus(@PathVariable String status)  {
        Map<String,Object> response = new HashMap<>();
        response.put("competitions",competitionService.getCompetitionByStatus(status).stream().map(competitionDtoConverter::convertCompetitionTODto).collect(Collectors.toList()));
        requestResponseWithDetails.setTimestamp(LocalDateTime.now());
        requestResponseWithDetails.setStatus("200");
        requestResponseWithDetails.setMessage("competitions retrieved successfully");
        requestResponseWithDetails.setDetails(response);
        return  ResponseEntity.ok().body(requestResponseWithDetails);
    }
}
