package com.app.fishcompetition.controllers;

import com.app.fishcompetition.common.responses.RequestResponse;
import com.app.fishcompetition.model.dto.FishDto;
import com.app.fishcompetition.services.FishService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class FishController {

    private final FishService fishService;


}
