package com.app.fishcompetition.controllers;

import com.app.fishcompetition.common.responses.RequestResponseWithDetails;
import com.app.fishcompetition.common.responses.RequestResponseWithoutDetails;
import com.app.fishcompetition.mapper.HuntingDtoConverter;
import com.app.fishcompetition.model.dto.HuntingDto;
import com.app.fishcompetition.model.entity.Hunting;
import com.app.fishcompetition.services.HuntingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class HuntingController {

    private final HuntingService huntingService;
    private final RequestResponseWithoutDetails requestResponseWithoutDetails;
    private final RequestResponseWithDetails   requestResponseWithDetails;
    private final HuntingDtoConverter huntingDtoConverter;
    @PostMapping("/hunting")
    public ResponseEntity<RequestResponseWithDetails>addHunting(@RequestBody @Valid HuntingDto huntingDto){
        Map<String,Object> response = new HashMap<>();
        Hunting huntingAdded = huntingService.addHunting(huntingDtoConverter.convertToEntity(huntingDto),huntingDto.getWeight());
        requestResponseWithDetails.setTimestamp(LocalDateTime.now());
        requestResponseWithDetails.setStatus("200");
        requestResponseWithDetails.setMessage("Hunting added successfully");
        response.put("hunting",huntingAdded);
        requestResponseWithDetails.setDetails(response);
        return ResponseEntity.ok().body(requestResponseWithDetails);
    }

    @GetMapping("/huntings")
    public ResponseEntity<RequestResponseWithDetails> getAllHunting(){
        Map<String,Object> response = new HashMap<>();
        response.put("huntings",huntingService.getAllHunting());
        requestResponseWithDetails.setTimestamp(LocalDateTime.now());
        requestResponseWithDetails.setStatus("200");
        requestResponseWithDetails.setMessage("Huntings retrieved successfully");
        requestResponseWithDetails.setDetails(response);
        return ResponseEntity.ok().body(requestResponseWithDetails);
    }
}
