package com.app.fishcompetition.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Competition {
    @Id
    @GeneratedValue(generator = "uuid2")
    private UUID id;

    private String code;

    private Date date;

    private Date startTime;

    private Date endTime;

    private int numberOfParticipants;

    private String location;

    @OneToMany(mappedBy = "competition")
    private List<Ranking> rankings;
}
