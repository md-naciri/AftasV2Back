package com.app.fishcompetition.controllers;

import com.app.fishcompetition.common.responses.RequestResponse;
import com.app.fishcompetition.mapper.LevelDtoConverter;
import com.app.fishcompetition.model.dto.LevelDto;
import com.app.fishcompetition.model.entity.Level;
import com.app.fishcompetition.services.LevelService;
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
public class LevelController {

    private final LevelService levelService;
    private final RequestResponse requestResponse;
    private final LevelDtoConverter levelDtoConverter;
    @PostMapping("/level")
    public ResponseEntity<RequestResponse> createLevel(@Valid @RequestBody  LevelDto levelDto) {
        Level level = levelDtoConverter.convertDtoTOLevel(levelDto);
        Level createdLevel = levelService.addLevel(level);
        requestResponse.setMessage("Levels created successfully");
        requestResponse.setTimestamp(LocalDateTime.now());
        requestResponse.setStatus("200");
        Map<String,Object> response = new HashMap<>();
        response.put("level",createdLevel);
        requestResponse.setDetails(response);
        return ResponseEntity.ok().body(requestResponse);
    }
}
