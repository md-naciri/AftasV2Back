package com.app.fishcompetition.model.entity;

import com.fasterxml.jackson.annotation.*;
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
public class Hunting {

    @Id
    @GeneratedValue(generator = "uuid2")
    private UUID id;

    private int numberOfFish;

    @ManyToOne
    @JoinColumn(name="member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name="fish_id")
    private Fish fish;

    @ManyToOne
    @JoinColumn(name="competition_id")
    private Competition competition;
}