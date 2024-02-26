package com.app.fishcompetition.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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

    @EmbeddedId
    private RankingKey id;

    @Column(name = "ranking_rank")
    private Integer rank;
    private Integer score;

    @ManyToOne
    @JoinColumn(name="member_id",insertable=false, updatable=false)
    private Member member;

    @ManyToOne
    @JoinColumn(name="competition_id",insertable=false, updatable=false)
    private Competition competition;
}
