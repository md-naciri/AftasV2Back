package com.app.fishcompetition.model.dto;

import com.app.fishcompetition.model.entity.Competition;
import com.app.fishcompetition.model.entity.Fish;
import com.app.fishcompetition.model.entity.Member;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class HuntingDto {

    @NotNull(message = "Member is required")
    private Member member;

    @NotNull(message = "Weight of hunt is required")
    private double weight;

    @NotNull(message = "Fish is required")
    private Fish fish;

    @NotNull(message = "competition  is required")
    private Competition competition;
}
