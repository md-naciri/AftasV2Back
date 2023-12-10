package com.app.fishcompetition.controllers;

import com.app.fishcompetition.common.responses.RequestResponseWithDetails;
import com.app.fishcompetition.common.responses.RequestResponseWithoutDetails;
import com.app.fishcompetition.mapper.LevelDtoConverter;
import com.app.fishcompetition.model.dto.LevelDto;
import com.app.fishcompetition.model.entity.Level;
import com.app.fishcompetition.services.LevelService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class LevelController {

    private final LevelService levelService;
    private final RequestResponseWithDetails requestResponseWithDetails;
    private final LevelDtoConverter levelDtoConverter;
    private final RequestResponseWithoutDetails requestResponseWithoutDetails;
    @PostMapping("/level")
    public ResponseEntity<RequestResponseWithDetails> createLevel(@Valid @RequestBody  LevelDto levelDto) {
        Level level = levelDtoConverter.convertDtoTOLevel(levelDto);
        Level createdLevel = levelService.addLevel(level);
        requestResponseWithDetails.setMessage("Levels created successfully");
        requestResponseWithDetails.setTimestamp(LocalDateTime.now());
        requestResponseWithDetails.setStatus("200");
        Map<String,Object> response = new HashMap<>();
        response.put("level",createdLevel);
        requestResponseWithDetails.setDetails(response);
        return ResponseEntity.ok().body(requestResponseWithDetails);
    }
    @DeleteMapping("/level/{id}")
    public ResponseEntity<RequestResponseWithoutDetails> deleteLevel(@PathVariable("id") UUID id) {
        levelService.deleteLevel(id);
        requestResponseWithoutDetails.setMessage("Level deleted successfully");
        requestResponseWithoutDetails.setTimestamp(LocalDateTime.now());
        requestResponseWithoutDetails.setStatus("200");
        return ResponseEntity.ok().body(requestResponseWithoutDetails);
    }
}
