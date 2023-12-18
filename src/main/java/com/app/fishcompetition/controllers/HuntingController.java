package com.app.fishcompetition.controllers;

import com.app.fishcompetition.common.responses.RequestResponseWithDetails;
import com.app.fishcompetition.common.responses.RequestResponseWithoutDetails;
import com.app.fishcompetition.mapper.CompetitionDtoConverter;
import com.app.fishcompetition.mapper.FishDtoConverter;
import com.app.fishcompetition.mapper.HuntingDtoConverter;
import com.app.fishcompetition.mapper.MemberDtoConverter;
import com.app.fishcompetition.model.dto.HuntingDto;
import com.app.fishcompetition.model.entity.*;
import com.app.fishcompetition.services.HuntingService;
import com.app.fishcompetition.services.impls.HuntingServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class HuntingController {

    private final HuntingService huntingService;
    private final RequestResponseWithoutDetails requestResponseWithoutDetails;
    private final RequestResponseWithDetails   requestResponseWithDetails;
    private final HuntingDtoConverter huntingDtoConverter;
    private final HuntingServiceImpl huntingServiceImpl;
    private  final MemberDtoConverter memberDtoConverter;
    private final CompetitionDtoConverter competitionDtoConverter;
    private final FishDtoConverter fishDtoConverter;
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
        List<Hunting> huntings = huntingService.getAllHunting();
        List<Map<String, Object>> huntingsData = huntings.stream()
                .map(this::convertHuntingToMap)
                .collect(Collectors.toList());
        response.put("huntings", huntingsData);
        requestResponseWithDetails.setTimestamp(LocalDateTime.now());
        requestResponseWithDetails.setStatus("200");
        requestResponseWithDetails.setMessage("Huntings retrieved successfully");
        requestResponseWithDetails.setDetails(response);
        return ResponseEntity.ok().body(requestResponseWithDetails);
    }
    private Map<String, Object> convertHuntingToMap(Hunting hunting) {
        Map<String, Object> huntingData = new HashMap<>();
        huntingData.put("id", hunting.getId().toString());
        huntingData.put("numberOfFish", hunting.getNumberOfFish());
        huntingData.put("member", memberDtoConverter.convertMemberTODto(hunting.getMember()));
        huntingData.put("fish", fishDtoConverter.convertFishTODto(hunting.getFish()));
        huntingData.put("competition", competitionDtoConverter.convertCompetitionTODto(hunting.getCompetition()));
        return huntingData;
    }
    private Map<String, Object> convertMemberToMap(Member member) {
        Map<String, Object> memberData = new HashMap<>();
        memberData.put("id", member.getId().toString());
        memberData.put("firstName", member.getFirstName());
        memberData.put("lastName", member.getLastName());
        memberData.put("identityNumber", member.getIdentityNumber());
        memberData.put("identityDocumentType", member.getIdentityDocumentType().toString());
        memberData.put("natitonality", member.getNationality());
        return memberData;
    }
    private Map<String, Object> convertFishToMap(Fish fish) {
        Map<String,Object> fishData = new HashMap<>();
        fishData.put("id",fish.getId().toString());
        fishData.put("name",fish.getName());
        fishData.put("level",convertLevelToMap(fish.getLevel()));
        fishData.put("average weight",fish.getAverageWeight());
        return fishData;
    }
    public Map<String,Object> convertLevelToMap(Level level){
        Map<String,Object> levelData = new HashMap<>();
        levelData.put("id",level.getId().toString());
        levelData.put("level",level.getLevel());
        levelData.put("code",level.getCode());
        levelData.put("points",level.getPoints());
        return levelData;
    }
    private Map<String, Object> convertMemberToMap(Competition competition) {
        Map<String, Object> competitionData = new HashMap<>();
        competitionData.put("id", competition.getId().toString());
        competitionData.put("code", competition.getCode());
        competitionData.put("startDate", competition.getStartTime());
        competitionData.put("endDate", competition.getEndTime());
        competitionData.put("location", competition.getLocation());
        competitionData.put("date", competition.getDate());
        return competitionData;
    }
    @GetMapping("/hunting/{huntingId}")
    public ResponseEntity<RequestResponseWithDetails> getHuntingById(@PathVariable("huntingId") UUID huntingId){
        Map<String,Object> response = new HashMap<>();
        response.put("hunting",huntingService.getHuntingById(huntingId));
        requestResponseWithDetails.setTimestamp(LocalDateTime.now());
        requestResponseWithDetails.setStatus("200");
        requestResponseWithDetails.setMessage("Hunting retrieved successfully");
        requestResponseWithDetails.setDetails(response);
        return ResponseEntity.ok().body(requestResponseWithDetails);
    }

    @GetMapping("/hunting/{memberId}/{competitionId}")
    public ResponseEntity<RequestResponseWithDetails> getHuntingById(@PathVariable UUID memberId,@PathVariable UUID competitionId){
        Map<String,Object> response = new HashMap<>();

        response.put("hunting",huntingServiceImpl.getAllHuntingOfMemberInCompetition(memberId,competitionId).get(0).getFish().getLevel().getPoints() );
        requestResponseWithDetails.setTimestamp(LocalDateTime.now());
        requestResponseWithDetails.setStatus("200");
        requestResponseWithDetails.setMessage("Hunting retrieved successfully");
        requestResponseWithDetails.setDetails(response);
        return ResponseEntity.ok().body(requestResponseWithDetails);
    }
}
