package com.app.fishcompetition.model.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id",scope = Competition.class)
public class Competition {
    @Id
    @GeneratedValue(generator = "uuid2")
    private UUID id;

    @Column(unique = true)
    private String code;

    @Temporal(TemporalType.DATE)
    private Date date;

    private Time startTime;

    private Time endTime;

    @Column(columnDefinition = "int default 0")
    private int numberOfParticipants;

    private String location;

    private Double amount;

    @OneToMany(mappedBy = "competition")
    private List<Ranking> rankings;

    @OneToMany(mappedBy = "competition")
    private List<Hunting> huntings;
}
