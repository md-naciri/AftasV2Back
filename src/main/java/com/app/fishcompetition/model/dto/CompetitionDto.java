package com.app.fishcompetition.model.dto;

import com.app.fishcompetition.model.entity.Ranking;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.sql.Time;
import java.util.Date;
import java.util.List;

@Data
public class CompetitionDto {
    @NotBlank(message = "Code cannot be blank")
    private String code;

    @NotNull(message = "Date cannot be null")
    @Future(message = "Date must be in the future")
    private Date date;

    @NotNull(message = "Start time cannot be null")
    private Time startTime;

    @NotNull(message = "End time cannot be null")
    private Time endTime;

    private int numberOfParticipants;

    @NotBlank(message = "Location cannot be blank")
    private String location;

    @NotNull(message = "Amount cannot be null")
    @Min(value = 1, message = "Amount must be greater than 0")
    private Double amount;

    @OneToMany(mappedBy = "competition")
    private List<Ranking> rankings;
}
