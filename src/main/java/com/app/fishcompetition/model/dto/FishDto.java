package com.app.fishcompetition.model.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.UUID;

@Data
public class FishDto {

    private UUID id;

    @NotBlank(message = "Name is mandatory")
    @Size(min = 3, max = 20, message = "Name must be between 3 and 20 characters")
    private String name;

    @NotNull(message = "Average weight is mandatory")
    @Min(value = 0, message = "Average weight must be greater than 0")
    private double averageWeight;


    @NotNull(message = "Level is mandatory")
    private LevelDto level;
}
