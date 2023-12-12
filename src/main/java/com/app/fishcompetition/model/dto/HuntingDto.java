package com.app.fishcompetition.model.dto;

import com.app.fishcompetition.model.entity.Fish;
import com.app.fishcompetition.model.entity.Member;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class HuntingDto {

    @NotNull(message = "Number of fish is required")
    @Min(value = 1, message = "Number of fish must be greater than 0")
    private int numberOfFish;

    @NotNull(message = "Member is required")
    @ManyToOne
    private Member member;

    @NotNull(message = "Fish is required")
    @ManyToOne
    private Fish fish;

}
