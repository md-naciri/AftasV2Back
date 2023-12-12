package com.app.fishcompetition.controllers;

import com.app.fishcompetition.common.responses.RequestResponseWithDetails;
import com.app.fishcompetition.common.responses.RequestResponseWithoutDetails;
import com.app.fishcompetition.model.dto.RankingDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RankingController {
    private final RequestResponseWithDetails requestResponseWithDetails;
    private final RequestResponseWithoutDetails    requestResponseWithoutDetails;

    @PostMapping("/ranking")
    public ResponseEntity<RequestResponseWithDetails> addRanking(@RequestBody  @Valid RankingDto rankingDto){

        return null;
    }
}
