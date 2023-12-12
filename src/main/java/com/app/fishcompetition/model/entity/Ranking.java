package com.app.fishcompetition.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ranking {
    @Id
    @GeneratedValue(generator = "uuid2")
    private UUID id;

    private int rank;

    private int score;

    @JsonBackReference
    @ManyToOne
    private Member member;

    @JsonBackReference
    @ManyToOne
    private Competition competition;
}
