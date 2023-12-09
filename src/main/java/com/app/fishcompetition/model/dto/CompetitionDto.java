package com.app.fishcompetition.model.dto;

import com.app.fishcompetition.model.entity.Ranking;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;
import java.util.List;

public class CompetitionDto {
    @NotBlank(message = "Code cannot be blank")
    private String code;

    @NotBlank(message = "Date cannot be blank")
    private Date date;
    @NotBlank(message = "Start time cannot be blank")
    @Future(message = "Start time must be in the future")
    private Date startTime;

    @NotBlank(message = "End time cannot be blank")
    @Future(message = "End time must be in the future")
    private Date endTime;

    @NotNull(message = "Number of participants cannot be null")
    @Min(value = 1, message = "Number of participants must be greater than 0")
    private int numberOfParticipants;

    @NotBlank(message = "Location cannot be blank")
    private String location;

    @OneToMany(mappedBy = "competition")
    private List<Ranking> rankings;
}
