package com.app.fishcompetition.model.entity;

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

    @ManyToOne
    private Member member;

    @ManyToOne
    private Competition competition;
}
