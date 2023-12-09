package com.app.fishcompetition.model.dto;

import com.app.fishcompetition.model.entity.Fish;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.List;

public class LevelDto {
    @NotNull(message = "Level is required")
    @Min(value = 10, message = "Level must be greater than 0")
    private int level;

    private String description;

    @NotNull(message = "Points are required")
    private int points;

    @OneToMany(mappedBy = "level")
    private List<Fish> fishes;
}
