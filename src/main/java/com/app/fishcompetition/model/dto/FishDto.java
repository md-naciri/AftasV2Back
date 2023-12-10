package com.app.fishcompetition.model.dto;

import com.app.fishcompetition.model.entity.Hunting;
import com.app.fishcompetition.model.entity.Level;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;


public class FishDto {

    @NotBlank(message = "Name is mandatory")
    @Size(min = 3, max = 20, message = "Name must be between 3 and 20 characters")
    private String name;

    @NotNull(message = "Average weight is mandatory")
    @Min(value = 0, message = "Average weight must be greater than 0")
    private double averageWeight;

    @OneToMany(mappedBy = "fish")
    private List<Hunting> huntings;

    @NotBlank(message = "Level is mandatory")
    @ManyToOne
    private Level level;
}
