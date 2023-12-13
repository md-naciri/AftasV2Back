package com.app.fishcompetition.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
    private Member member;

    @ManyToOne
    private Fish fish;

}