package com.app.fishcompetition.controllers;

import com.app.fishcompetition.common.responses.RequestResponseWithDetails;
import com.app.fishcompetition.common.responses.RequestResponseWithoutDetails;
import com.app.fishcompetition.mapper.FishDtoConverter;
import com.app.fishcompetition.model.dto.CompetitionDto;
import com.app.fishcompetition.model.dto.FishDto;
import com.app.fishcompetition.model.entity.Competition;
import com.app.fishcompetition.model.entity.Fish;
import com.app.fishcompetition.services.FishService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class FishController {

    private final FishService fishService;
    private final FishDtoConverter fishDtoConverter;
    private final RequestResponseWithoutDetails requestResponseWithoutDetails;
    private final RequestResponseWithDetails requestResponseWithDetails;
    private final ModelMapper modelMapper;

    @PostMapping("/fish")
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    public ResponseEntity<RequestResponseWithDetails> addFish(@Valid @RequestBody FishDto fishDto) {

        Fish fish = fishDtoConverter.convertDtoToFish(fishDto);
        FishDto savedFish = fishDtoConverter.convertFishTODto(fishService.addFish(fish));
        Map<String,Object> response = new HashMap<>();
        requestResponseWithDetails.setTimestamp(LocalDateTime.now());
        requestResponseWithDetails.setMessage("Fish added successfully");
        requestResponseWithDetails.setStatus("200");
        response.put("Fish",savedFish);
        requestResponseWithDetails.setDetails(response);
        return ResponseEntity.ok().body(requestResponseWithDetails);
    }

    @GetMapping("/fish")
    @PreAuthorize("hasRole('ROLE_ADHERENT')")
    public ResponseEntity<RequestResponseWithDetails> getAllFish() {
        Map<String,Object> response = new HashMap<>();
        List<Fish> fishes = fishService.getAllFish();
        List<FishDto> fishesData = fishes.stream()
                .map(fishDtoConverter::convertFishTODto)
                .collect(Collectors.toList());
        response.put("fishes",fishesData);
        requestResponseWithDetails.setTimestamp(LocalDateTime.now());
        requestResponseWithDetails.setMessage("Fish retrieved successfully");
        requestResponseWithDetails.setStatus("200");
        requestResponseWithDetails.setDetails(response);
        return ResponseEntity.ok().body(requestResponseWithDetails);
    }
    @GetMapping("/fishes/{pageNumber}/{pageSize}")
    @PreAuthorize("hasRole('ROLE_ADHERENT')")
    public ResponseEntity<RequestResponseWithDetails> getFishWithPagination(@PathVariable int pageNumber, @PathVariable int pageSize) {
        Map<String,Object> response = new HashMap<>();
        response.put("fishes", fishService.getAllFishWithPagination(pageNumber, pageSize).stream().map(fishDtoConverter::convertFishTODto).collect(Collectors.toList()));
        requestResponseWithDetails.setTimestamp(LocalDateTime.now());
        requestResponseWithDetails.setMessage("Fish retrieved successfully");
        requestResponseWithDetails.setStatus("200");
        requestResponseWithDetails.setDetails(response);
        return ResponseEntity.ok().body(requestResponseWithDetails);
    }
    @DeleteMapping("/fish/{id}")
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    public ResponseEntity<RequestResponseWithoutDetails> deleteFish(@PathVariable  UUID id) {
        fishService.deleteFish(id);
        requestResponseWithoutDetails.setTimestamp(LocalDateTime.now());
        requestResponseWithoutDetails.setMessage("Fish deleted successfully");
        requestResponseWithoutDetails.setStatus("200");
        return ResponseEntity.ok().body(requestResponseWithoutDetails);
    }

}
