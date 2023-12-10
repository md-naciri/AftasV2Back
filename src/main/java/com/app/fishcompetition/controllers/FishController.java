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
        response.put("Fish",savedFish);
        requestResponseWithDetails.setDetails(response);
        return ResponseEntity.ok().body(requestResponseWithDetails);
    }

}
