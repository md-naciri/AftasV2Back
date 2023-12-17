package com.app.fishcompetition.controllers;

import com.app.fishcompetition.common.responses.RequestResponseWithDetails;
import com.app.fishcompetition.common.responses.RequestResponseWithoutDetails;
import com.app.fishcompetition.mapper.FishDtoConverter;
import com.app.fishcompetition.model.dto.FishDto;
import com.app.fishcompetition.model.entity.Fish;
import com.app.fishcompetition.services.FishService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class FishController {

    private final FishService fishService;
    private final FishDtoConverter fishDtoConverter;
    private final RequestResponseWithoutDetails requestResponseWithoutDetails;
    private final RequestResponseWithDetails requestResponseWithDetails;
    @PostMapping("/fish")
    public ResponseEntity<RequestResponseWithDetails> addFish(@Valid @RequestBody FishDto fishDto) {

        Fish fish = fishDtoConverter.convertDtoToFish(fishDto);
        Fish savedFish = fishService.addFish(fish);
        Map<String,Object> response = new HashMap<>();
        requestResponseWithDetails.setTimestamp(LocalDateTime.now());
        requestResponseWithDetails.setMessage("Fish added successfully");
        requestResponseWithDetails.setStatus("200");
        response.put("Fish",savedFish);
        requestResponseWithDetails.setDetails(response);
        return ResponseEntity.ok().body(requestResponseWithDetails);
    }

    @GetMapping("/fish")
    public ResponseEntity<RequestResponseWithDetails> getAllFish() {
        Map<String,Object> response = new HashMap<>();
        List<Fish> fishes = fishService.getAllFish();
        List<FishDto> fishesDto  = new ArrayList<>();
        for(Fish fish: fishes){
            fishesDto.add(fishDtoConverter.convertFishTODto(fish));
        }
        response.put("fishes",fishesDto);
        requestResponseWithDetails.setTimestamp(LocalDateTime.now());
        requestResponseWithDetails.setMessage("Fish retrieved successfully");
        requestResponseWithDetails.setStatus("200");
        requestResponseWithDetails.setDetails(response);
        return ResponseEntity.ok().body(requestResponseWithDetails);
    }
    @DeleteMapping("/fish/{id}")
    public ResponseEntity<RequestResponseWithoutDetails> deleteFish(@PathVariable  UUID id) {
        fishService.deleteFish(id);
        requestResponseWithoutDetails.setTimestamp(LocalDateTime.now());
        requestResponseWithoutDetails.setMessage("Fish deleted successfully");
        requestResponseWithoutDetails.setStatus("200");
        return ResponseEntity.ok().body(requestResponseWithoutDetails);
    }

}
