package com.app.fishcompetition.model.dto;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.util.UUID;

@Data
public class LevelDto {


    private UUID id;

    @NotBlank(message = "Code is required")
    private String code;

    @NotNull(message = "Level is required")
    @Min(value = 1,message = "Level must be greater than 0")
    private int level;

    @NotBlank(message = "Description is required")
    private String description;

    @NotNull(message = "Points are required")
    @Min(value = 1,message = "Points must be greater than 0")
    private int points;
}
