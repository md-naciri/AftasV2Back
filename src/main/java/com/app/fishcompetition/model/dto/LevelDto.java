package com.app.fishcompetition.model.dto;

import com.app.fishcompetition.model.entity.Fish;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import java.util.List;

public class LevelDto {

    @NotBlank(message = "Code is required")
    @Size(min = 3, message = "Code must be at least 3 characters long")
    private String code;
    @NotNull(message = "Level is required")
    @Min(value = 10, message = "Level must be greater than 0")
    private int level;

    private String description;

    @NotNull(message = "Points are required")
    private int points;

    @OneToMany(mappedBy = "level")
    private List<Fish> fishes;
}
