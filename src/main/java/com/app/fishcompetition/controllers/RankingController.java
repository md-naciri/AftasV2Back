package com.app.fishcompetition.controllers;

import com.app.fishcompetition.common.responses.RequestResponseWithDetails;
import com.app.fishcompetition.common.responses.RequestResponseWithoutDetails;
import com.app.fishcompetition.mapper.RankingDtoConverter;
import com.app.fishcompetition.model.dto.RankingDto;
import com.app.fishcompetition.model.entity.Ranking;
import com.app.fishcompetition.services.RankingService;
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
@RequestMapping("/api/")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RankingController {

    private final RequestResponseWithDetails requestResponseWithDetails;
    private final RequestResponseWithoutDetails    requestResponseWithoutDetails;
    private final RankingService rankingService;
    private final RankingDtoConverter rankingDtoConverter;

    @PostMapping("/ranking")
    public ResponseEntity<RequestResponseWithDetails> addRanking(@RequestBody  @Valid RankingDto rankingDto){
        Ranking rankingToAdd = rankingService.addRanking(rankingDtoConverter.convertToEntity(rankingDto));
        Map<String,Object> response = new HashMap<>();
        requestResponseWithDetails.setTimestamp(LocalDateTime.now());
        requestResponseWithDetails.setStatus("200");
        requestResponseWithDetails.setMessage("Ranking added successfully");
        response.put("Ranking",rankingToAdd);
        requestResponseWithDetails.setDetails(response);
        return ResponseEntity.ok().body(requestResponseWithDetails);
    }
    @GetMapping("/rankings")
    public ResponseEntity<RequestResponseWithDetails> getAllRanking(){
        Map<String,Object> response = new HashMap<>();
        requestResponseWithDetails.setTimestamp(LocalDateTime.now());
        requestResponseWithDetails.setStatus("200");
        requestResponseWithDetails.setMessage("Rankings retrieved successfully");
        response.put("Rankings",rankingService.getRankings());
        requestResponseWithDetails.setDetails(response);
        return ResponseEntity.ok().body(requestResponseWithDetails);
    }
}
