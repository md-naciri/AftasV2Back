package com.app.fishcompetition.model.dto;

import com.app.fishcompetition.model.dto.response.MemberDTO;
import com.app.fishcompetition.model.entity.Fish;
import com.app.fishcompetition.model.entity.Member;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class HuntingDto {

    @NotNull(message = "Member is required")
    private MemberDTO member;

    @NotNull(message = "Weight of hunt is required")
    private double weight;

    @NotNull(message = "Fish is required")
    private FishDto fish;

    @NotNull(message = "competition  is required")
    private CompetitionDto competition;
}
