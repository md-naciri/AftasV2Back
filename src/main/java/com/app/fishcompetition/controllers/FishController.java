package com.app.fishcompetition.controllers;

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

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class FishController {

    private final FishService fishService;
    private final FishDtoConverter fishDtoConverter;
    @PostMapping("/fish")
    public ResponseEntity<Fish> addFish(@Valid @RequestBody FishDto fishDto) {
        Fish fish = fishDtoConverter.convertDtoToFish(fishDto);
        return ResponseEntity.ok().body(fishService.addFish(fish));
    }

}
